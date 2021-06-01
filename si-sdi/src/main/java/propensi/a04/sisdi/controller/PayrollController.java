package propensi.a04.sisdi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import propensi.a04.sisdi.model.DokumenTotalModel;
import propensi.a04.sisdi.model.GajiModel;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.KomponenPengaliModel;
import propensi.a04.sisdi.repository.DokumenTotalDb;
import propensi.a04.sisdi.repository.GajiDb;
import propensi.a04.sisdi.repository.KaryawanDb;
import propensi.a04.sisdi.repository.KomponenPengaliDb;
import propensi.a04.sisdi.service.PayrollService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/payroll")
public class PayrollController {
    @Autowired
    PayrollService payrollService;

    @Autowired
    KomponenPengaliDb komponenPengaliDb;

    @Autowired
    DokumenTotalDb dokumenTotalDb;

    @Autowired
    KaryawanDb karyawanDb;

    @Autowired
    GajiDb gajiDb;

    //Request Param tanggal dari button payroll yang ada di navbar
    @GetMapping("")
    private String requestPayrollForm(
            @RequestParam(value="tanggalIsu") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggalIsu,
            HttpServletRequest request,
            Model model) {
        //Initiate Page
        YearMonth reset = payrollService.datetoYM(tanggalIsu);
        Date tanggal_isu = payrollService.YMtoDate(reset);
        try{
            payrollService.getDokumenByTanggal_Isu(tanggal_isu);
        }catch(NoSuchElementException exception){
            DokumenTotalModel dokumenBaru = new DokumenTotalModel();
            payrollService.makeDokumenTotal(dokumenBaru, tanggal_isu);
        }
        DokumenTotalModel dokumen = payrollService.getDokumenByTanggal_Isu(tanggal_isu);
        payrollService.updateDokumenTotal(dokumen);

        //Authorization Control
        String role = payrollService.findUser().getId_role().getRole();

        if((role.equals("Wakil Pengurus Harian"))&&
                (dokumen.getId_status().getStatus().equals("Menunggu Pengajuan"))){
            String message="Mohon maaf, Anda belum memiliki akses ke halaman ini " +
                    "karena Manajer SDI belum mengajukan dokumen total anggaran";
            model.addAttribute("message", message);
            return "payroll-access";
        } else{
            //List Attribute
            List<DokumenTotalModel> dokumenList = dokumenTotalDb.findAll();
            int lastIndex = dokumenList.size()-1;
            List<List<Integer>> total = payrollService.totalPerDokPerUnit();
            List<String> dateList = payrollService.displayYearMonth();
            model.addAttribute("role", role);
            model.addAttribute("dateList", dateList);
            model.addAttribute("dateDisplay", dateList.get(lastIndex));
            model.addAttribute("dokumenDisplay", dokumenList.get(lastIndex));
            model.addAttribute("dokumenList", dokumenList);
            model.addAttribute("unitDisplay", total.get(lastIndex));
            model.addAttribute("total", total);
            return "mengajukan-payroll";
        }
    }


    // Notifikasi Pengajuan Dokumen Total Gaji
    @RequestMapping(value="", method = RequestMethod.POST)
    private String ajukanDokumenGaji(
            @ModelAttribute DokumenTotalModel dokumenSubmit,
            @RequestParam(value="action", required=true) String action,
            Model model) {
        String message;
        String flag;
        String date = payrollService.displayYearMonth(dokumenSubmit.getTanggalIsu());
        switch(action){
            case "ajukan":
                try{
                    payrollService.statusToAjukan(dokumenSubmit);
                    message = "Dokumen Total Gaji untuk isu " + date + " berhasil diajukan";
                    flag="0";
                }catch(Exception e){
                    message = "Mohon maaf, Dokumen Total Gaji untuk isu " + date + " gagal diajukan";
                    flag="1";
                }
                break;
            case "requestAkses":
                try{
                    payrollService.statusToMenungguAkses(dokumenSubmit);
                    message = "Request untuk melihat dokumen total anggaran " +
                            "untuk isu " + date + " berhasil diajukan";
                    flag="0";
                }catch(Exception e){
                    message = "Mohon maaf, Request untuk melihat dokumen total anggaran " + date + " gagal diajukan";
                    flag="1";
                }
                break;
            case "grantAkses":
                try{
                    payrollService.statusToAksesDiberikan(dokumenSubmit);
                    message = "Request untuk melihat dokumen total anggaran " +
                            "untuk isu " + date + " berhasil diberikan";
                    flag="0";
                }catch(Exception e){
                    message = "Mohon maaf, Request untuk melihat dokumen total anggaran " + date + " gagal diberikan";
                    flag="1";
                }
                break;
            default:
                try{
                    payrollService.statusToDisetujui(dokumenSubmit);
                    message = "Payroll untuk " + date + " berhasil disetujui";
                    flag="0";
                }catch(Exception e){
                    message = "Mohon maaf, Payroll untuk " + date + " gagal disetujui";
                    flag="1";
                }
                break;
        }

        model.addAttribute("message", message);
        model.addAttribute("flag", flag);
        return "payroll-notifs";
    }

    @GetMapping("/detailPayroll")
    private String detailPayroll(@RequestParam(value="dokumenId") Long dokumenId,
                                 @RequestParam(value="unitName") String unitName,
                                 Model model) {
        DokumenTotalModel dokumen = dokumenTotalDb.findById(dokumenId).get();
        List<GajiModel> gajiList = gajiDb.findByUnitAndDokumen(unitName, dokumen);
        List<KaryawanModel> karyawanList = new ArrayList<>();
        for(GajiModel gaji:gajiList){
            karyawanList.add(gaji.getId_karyawan());
        }
        Integer totalAnggaranUnit = payrollService.totalAnggaranUnit(unitName, dokumen);
        model.addAttribute("periodeDokumen", payrollService.displayYearMonth(
                dokumen.getTanggalIsu()
        ));
        model.addAttribute("dokumen", dokumen);
        model.addAttribute("totalAnggaranUnit", totalAnggaranUnit);
        model.addAttribute("karyawanList", karyawanList);
        model.addAttribute("unitName", unitName);
        model.addAttribute("gajiList", gajiList);
        return "detail-payroll";
    }

    @GetMapping("/listGaji")
    private String listGajiKaryawan(
            @RequestParam(value="idKaryawan") Long idKaryawan,
            Model model){
        KaryawanModel karyawan = payrollService.findUser().getKaryawanModel();
        Long karyawanId = karyawan.getId();
        if (idKaryawan != karyawanId) {
            String message = "Mohon maaf, Anda tidak memiliki akses ke halaman ini";
            model.addAttribute("message", message);
            return "payroll-access";
        }else if(karyawan.getGajiModel()==null){
            String message = "Mohon maaf, Anda belum memiliki Gaji";
            model.addAttribute("message", message);
            return "payroll-access";
        }else{
            List<GajiModel> gajiList = karyawan.getGajiModel();
            List<GajiModel> gajiFix = new ArrayList<>();
            List<String> dateList = new ArrayList<>();
            for(GajiModel gaji:gajiList){
                if(gaji.getDokumen().getId_status().getStatus().equals("Disetujui")){
                    gajiFix.add(gaji);
                    dateList.add(payrollService.displayYearMonth(gaji.getDokumen().getTanggalIsu()));
                }
            }
            model.addAttribute("gajiList", gajiFix);
            model.addAttribute("dateList", dateList);
            model.addAttribute("karyawan", karyawan);
            return "payroll-list";

        }

    }

    // View Detail Gaji per Karyawan
    @GetMapping("/detailGaji")
    private String detailGaji(
            @RequestParam(value="idGaji") Long idGaji,
            Model model){
       GajiModel gaji = gajiDb.findById(idGaji).get();
       KaryawanModel karyawanParam = gaji.getId_karyawan();
       KaryawanModel karyawanUser = payrollService.findUser().getKaryawanModel();
       String date = payrollService.displayYearMonth(gaji.getDokumen().getTanggalIsu());
       String status = payrollService.displayStatus(karyawanParam.getStatusKaryawan());
       String role = payrollService.findUser().getId_role().getRole();
       String statusDokumen = gaji.getDokumen().getId_status().getStatus();
       if((role.equals("Karyawan")) && (!statusDokumen.equals("Disetujui"))){
           String message="Mohon maaf, slip gaji untuk " + date + " belum dapat diakses. Silahkan hubungi Manajer SDI" +
                   " untuk keterangan lebih lanjut.";
           model.addAttribute("message", message);
           return "payroll-access";
       }else if(role.equals("Karyawan")){
           if(!karyawanParam.getKaryawan().equals(karyawanUser.getKaryawan())){
                String message="Mohon maaf, Anda tidak memiliki akses ke halaman ini";
                model.addAttribute("message", message);
                return "payroll-access";
            }
       } else{
       }
        model.addAttribute("statusDokumen", statusDokumen);
        model.addAttribute("tahunAngkat", payrollService.displayYearMonth(karyawanParam.getTanggalMasuk()));
        model.addAttribute("role", role);
        model.addAttribute("date", date);
        model.addAttribute("status", status);
        model.addAttribute("karyawan", karyawanParam);
        model.addAttribute("gaji", gaji);
        return "detail-gaji";
    }

    @GetMapping("/detailGaji/ubah")
    private String detailGajiForm(
            @RequestParam(value="idGaji") Long idGaji,
            HttpServletRequest request,
            Model model){
        Long gajiId = Long.valueOf(request.getParameter("idGaji"));
        GajiModel gaji = gajiDb.findById(gajiId).get();
        KaryawanModel karyawan = karyawanDb.findByGajiModel(gaji).get();
        String status;
        status="Tetap";
        if(karyawan.getStatusKaryawan()==2){
            status="Kontrak";
        } else if(karyawan.getStatusKaryawan()==3){
            status="Tenaga Ahli";
        }
        Date date = gaji.getDokumen().getTanggalIsu();
        YearMonth yearMonth =
                YearMonth.from(date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
        model.addAttribute("yearMonth", yearMonth);
        model.addAttribute("karyawan", karyawan);
        model.addAttribute("gaji", gaji);
        model.addAttribute("status", status);
        return "detail-gaji-form";
    }

    // Notifikasi Ubah Gaji
    @RequestMapping(value="/detailGaji/ubah", method = RequestMethod.POST)
    private String detailGajiSubmit(
            @ModelAttribute GajiModel gaji,
            Model model) {
        KaryawanModel karyawan = gaji.getId_karyawan();
        DokumenTotalModel dokumen = gaji.getDokumen();
        payrollService.simpanGajiKaryawan(gaji, karyawan, dokumen);
        model.addAttribute("gaji", gaji);
        String message = "Gaji berhasil diubah.";
        model.addAttribute("message", message);
        return "payroll-notifs";
    }

    // View Komponen Pengali Gaji
    @GetMapping("/komponenGaji")
    private String komponenGaji(Model model){
        KomponenPengaliModel komponenGaji = payrollService.getKomponenGajiById(Long.parseLong("1"));
        model.addAttribute("komponenGaji", komponenGaji);
        return"komponen-pengali-gaji";
    }

    // Form Ubah Komponen Gaji
    @GetMapping("/komponenGaji/ubah")
    private String ubahKomponenGajiForm(
            @RequestParam(value="id") Long id,
            Model model) {
        KomponenPengaliModel komponenGaji = payrollService.getKomponenGajiById(id);
        model.addAttribute("komponenGaji", komponenGaji);
        return "komponen-pengali-gaji-form";
    }

    // Notifikasi Ubah Komponen Gaji
    @RequestMapping(value="/komponenGaji/ubah", method = RequestMethod.POST)
    private String ubahKomponenGajiSubmit(
            @ModelAttribute KomponenPengaliModel komponenGaji,
            Model model) {
        payrollService.ubahKomponenGaji(komponenGaji);
        model.addAttribute("komponenGaji", komponenGaji);
        model.addAttribute("message", "Komponen Gaji berhasil diubah.");
        return "payroll-notifs";
    }

}
