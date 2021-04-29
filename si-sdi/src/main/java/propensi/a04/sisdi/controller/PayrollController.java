package propensi.a04.sisdi.controller;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @GetMapping("")
    private String requestPayrollForm(Model model) {
        YearMonth now = YearMonth.now();
        final Date date =
                Date.from(now.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        // Generate Dokumen Total Anggaran baru kalau belum ada untuk periode itu
        if(!payrollService.compareDokumenTotalByTanggalIsu(now) || dokumenTotalDb.findAll().isEmpty()){
            // Buat dokumen baru dan populate+save datanya
            DokumenTotalModel dokumenBaru = new DokumenTotalModel();
            payrollService.makeDokumenTotal(dokumenBaru);
        }
        DokumenTotalModel dokumen = dokumenTotalDb.findDokumenTotalByTanggalIsu(date).get();
        payrollService.updateDokumenTotal(dokumen);
        List<DokumenTotalModel> dokumenList = dokumenTotalDb.findAll();
        int lastIndex = dokumenList.size()-1;
        DokumenTotalModel dokumenDisplay = dokumenList.get(lastIndex);
        List<List<Integer>> total = payrollService.totalPerDokPerUnit();
        List<Integer> unitDisplay = total.get(lastIndex);
        model.addAttribute("now", now);
        model.addAttribute("dokumenDisplay", dokumenDisplay);
        model.addAttribute("dokumenList", dokumenList);
        model.addAttribute("unitDisplay", unitDisplay);
        model.addAttribute("total", total);
        return "mengajukan-payroll";
    }

    // Notifikasi Pengajuan Dokumen Total Gaji
    @GetMapping(value="/ajukan")
    private String ajukanDokumenGaji(
            @RequestParam(value="idDok") Long idDok,
            HttpServletRequest request,
            Model model) {
        Long dokId = Long.valueOf(request.getParameter("idDok"));
        DokumenTotalModel ajukan = dokumenTotalDb.findById(dokId).get();
        payrollService.ajukanDokumenTotalGaji(ajukan);
        Date date = ajukan.getTanggalIsu();
        String message = "Dokumen Total Gaji untuk isu" + date + "berhasil diajukan";
        model.addAttribute("message", message);
        return "payroll-notifs";
    }

    @GetMapping("/detailPayroll")
    private String detailPayroll(@RequestParam(value="dokumenId") Long dokumenId,
                                 @RequestParam(value="unitName") String unitName,
                                 HttpServletRequest request,
                                 Model model) {
        Long idDokumen = Long.valueOf(request.getParameter("dokumenId"));
        DokumenTotalModel dokumen = dokumenTotalDb.findById(idDokumen).get();
        String namaUnit = String.valueOf(request.getParameter("unitName"));
        List<GajiModel> gajiList = gajiDb.findByUnitAndDokumen(namaUnit, dokumen);
        List<KaryawanModel> karyawanList = new ArrayList<>();
        for(GajiModel gaji:gajiList){
            karyawanList.add(gaji.getId_karyawan());
        }
        Integer totalAnggaranUnit = payrollService.totalAnggaranUnit(namaUnit, dokumen);
        model.addAttribute("totalAnggaranUnit", totalAnggaranUnit);
        model.addAttribute("karyawanList", karyawanList);
        model.addAttribute("unitName", unitName);
        model.addAttribute("gajiList", gajiList);
        return "detail-payroll";
    }

    // View Detail Gaji per Karyawan
    @GetMapping("/detailGaji")
    private String detailGaji(
            @RequestParam(value="idGaji") Long idGaji,
            HttpServletRequest request,
            Model model){
        Long gajiId = Long.valueOf(request.getParameter("idGaji"));
       GajiModel gaji = gajiDb.findById(gajiId).get();
       KaryawanModel karyawan = karyawanDb.findByGajiModel(gaji).get();
       Date date = gaji.getDokumen().getTanggalIsu();
        YearMonth yearMonth =
                YearMonth.from(date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
        String status;
        status="Tetap";
        if(karyawan.getStatusKaryawan()==2){
            status="Kontrak";
        } else if(karyawan.getStatusKaryawan()==3){
            status="Tenaga Ahli";
        }
       model.addAttribute("yearMonth", yearMonth);
       model.addAttribute("status", status);
       model.addAttribute("karyawan", karyawan);
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
