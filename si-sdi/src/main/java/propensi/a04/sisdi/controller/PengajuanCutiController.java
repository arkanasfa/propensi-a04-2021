package propensi.a04.sisdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import propensi.a04.sisdi.model.*;

import propensi.a04.sisdi.repository.KaryawanDb;
import propensi.a04.sisdi.repository.PengajuanCutiDb;
import propensi.a04.sisdi.repository.StatusDB;
import propensi.a04.sisdi.service.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class PengajuanCutiController {
    @Autowired
    PengajuanCutiService pengajuanCutiService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    StatusService statusService;

    @Autowired
    UserService userService;

    @Autowired
    PimpinanUnitService pimpinanUnitService;

    @Autowired
    PengajuanCutiDb pengajuanCutiDb;

    @Autowired
    KaryawanDb karyawanDb;

    @Autowired
    StatusDB statusDb;

    @RequestMapping("/cuti")
    public String viewAllCuti(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usern = auth.getName();
        UserModel user1 = userService.findbyUsername(usern);
        String roleName = user1.getId_role().getRole();

        StatusModel diajukan = statusService.getStatusById(Long.valueOf(1));
        StatusModel accPU = statusService.getStatusById(Long.valueOf(2));
        StatusModel accKB = statusService.getStatusById(Long.valueOf(3));
        StatusModel evalPU= statusService.getStatusById(Long.valueOf(10));
        StatusModel evalKB= statusService.getStatusById(Long.valueOf(11));
        StatusModel evalMSDI= statusService.getStatusById(Long.valueOf(16));
        KaryawanModel karyawan = karyawanService.getKaryawanbyId(user1.getId());
        if(roleName.equals("Karyawan")){
            int sisaCuti = 12-karyawan.getJumlahCuti();
            List<PengajuanCutiModel> newList = pengajuanCutiService.getCutiList();
            model.addAttribute("cutiList", newList);
            model.addAttribute("sisaCuti", sisaCuti);
        }
        else if (roleName.equals("Pimpinan Unit")){
            PimpinanUnitModel PU = pimpinanUnitService.getPimpinanUnitByIdKaryawan(karyawan);
            int sisaCutiPU = 12-karyawan.getJumlahCuti();
            List<PengajuanCutiModel> forPUA = pengajuanCutiService.getPengajuanCutiById_Status(diajukan);
            List<PengajuanCutiModel> forPUB = pengajuanCutiService.getPengajuanCutiById_Status(evalPU);
            List<PengajuanCutiModel> newList = Stream.concat(forPUA.stream(), forPUB.stream())
                    .collect(Collectors.toList());
            model.addAttribute("cutiList", newList);
        }
        else if (roleName.equals("Kepala Bagian")){
            List<PengajuanCutiModel> forKBA = pengajuanCutiService.getPengajuanCutiById_Status(accPU);
            List<PengajuanCutiModel> forKBB = pengajuanCutiService.getPengajuanCutiById_Status(evalKB);
            List<PengajuanCutiModel> newList = Stream.concat(forKBA.stream(), forKBB.stream())
                    .collect(Collectors.toList());
            model.addAttribute("cutiList", newList);
        }
        else {
            List<PengajuanCutiModel> forMSDIA = pengajuanCutiService.getPengajuanCutiById_Status(accKB);
            List<PengajuanCutiModel> forMSDIB = pengajuanCutiService.getPengajuanCutiById_Status(evalMSDI);
            List<PengajuanCutiModel> newList = Stream.concat(forMSDIA.stream(), forMSDIB.stream())
                    .collect(Collectors.toList());
            model.addAttribute("cutiList", newList);
        }
        return "view-all-cuti";
    }

    @RequestMapping(path = "/cuti/detail")
    public String detailCuti(
        @RequestParam(value="id") Long id, Model model){
        PengajuanCutiModel cuti = pengajuanCutiService.getCutiById(id);
        model.addAttribute("cuti", cuti);
        return "detail-cuti";
    }

    @RequestMapping(path = "/cuti/add")
    public String addCuti(Model model){
        PengajuanCutiModel cuti = new PengajuanCutiModel();
        model.addAttribute("cuti", cuti);
        return "form-add-cuti";
    }

    @RequestMapping(path = "/cuti/add", method = RequestMethod.POST)
    public String addCutiSubmit(@ModelAttribute PengajuanCutiModel cuti, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usern = auth.getName();
        UserModel user1 = userService.findbyUsername(usern);
//        if KARYAWAN
            KaryawanModel karyawan = karyawanService.getKaryawanbyId(user1.getId());
            cuti.setId_karyawan(karyawan);
//        if(karyawan.getJumlahCuti()+cuti.getDurasi()<=12) {
            try {
                String kodeCuti = pengajuanCutiService.generateKodeCuti(cuti);
                cuti.setKode_cuti(kodeCuti);
                StatusModel id_status = statusService.getStatusById(Long.valueOf(1));
                cuti.setIdstatus(id_status);
                Date date = new Date();
                cuti.setTanggalRequest(date);
                int durasi = pengajuanCutiService.generateDurasi(cuti);
                int gap = pengajuanCutiService.generateStartValid(cuti);
                if(durasi>0 && durasi<13 && gap < 0) {
                    cuti.setDurasi(durasi);
                    pengajuanCutiService.addCuti(cuti);
                    model.addAttribute("kode_cuti", cuti.getKode_cuti());
                    return "add-cuti";
                } else {
                    return "notifikasi-gagal-durasi-cuti";
                }
            } catch (NullPointerException nullException) {
                return "notifikasi-gagal-add-cuti";
            }
//        }
//        }
//        else {
//            return "notifikasi-gagal-durasi-cuti";
//        }
    }

    @RequestMapping(path = "/cuti/edit")
    public String editCutiForm(@RequestParam(value="id") Long id, Model model){
        PengajuanCutiModel existingCuti = pengajuanCutiService.getCutiById(id);
        model.addAttribute("cuti", existingCuti);
        return "form-change-cuti";
    }

    @RequestMapping(path = "/cuti/edit", method = RequestMethod.POST)
    public String editCutiSubmit(@ModelAttribute PengajuanCutiModel cuti, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usern = auth.getName();
        UserModel user1 = userService.findbyUsername(usern);
//        KARYAWAN
        KaryawanModel karyawan = karyawanService.getKaryawanbyId(user1.getId());
        cuti.setId_karyawan(karyawan);
        try {
            String kodeCuti = pengajuanCutiService.generateKodeCuti(cuti);
            cuti.setKode_cuti(kodeCuti);
            int durasi = pengajuanCutiService.generateDurasi(cuti);
            int gap = pengajuanCutiService.generateStartValid(cuti);
            if (durasi > 0 && durasi < 13 && gap < 0) {
                cuti.setDurasi(durasi);
                pengajuanCutiService.changeCuti(cuti);
                model.addAttribute("kode_cuti", cuti.getKode_cuti());
                return "edit-cuti";
            } else {
                return "notifikasi-gagal-durasi-cuti";
            }
        }
        catch (NullPointerException nullException){
            return "notifikasi-gagal-ubah-cuti";
        }
//        }
//        else {
//            return "notifikasi-gagal-durasi-cuti";
//        }

    }

    @RequestMapping(path = "/cuti/delete")
    public String deleteCuti(@RequestParam(value="id") Long idCuti, Model model){
        PengajuanCutiModel existingCuti = pengajuanCutiService.getCutiById(idCuti);
        String kodeCuti = existingCuti.getKode_cuti();
        StatusModel diajukan = statusService.getStatusById(Long.valueOf(1));
        StatusModel accPU = statusService.getStatusById(Long.valueOf(2));
        StatusModel accKB = statusService.getStatusById(Long.valueOf(3));
        StatusModel evalPU= statusService.getStatusById(Long.valueOf(10));
        StatusModel evalKB= statusService.getStatusById(Long.valueOf(11));
        StatusModel evalMSDI= statusService.getStatusById(Long.valueOf(16));
        StatusModel dibatalkan= statusService.getStatusById(Long.valueOf(4));
        StatusModel ditolak= statusService.getStatusById(Long.valueOf(5));
        StatusModel disetujui= statusService.getStatusById(Long.valueOf(7));
//        if (existingCuti.getIdstatus().equals(disetujui)){
//            pengajuanCutiService.batalkanCuti(existingCuti);
//            int allow = 0;
//            model.addAttribute("flag", allow);
//        }
//        else{
//            pengajuanCutiService.deleteCuti(existingCuti);
//            int allow = 1;
//            model.addAttribute("flag", allow);
//        }
        model.addAttribute("kode_cuti", kodeCuti);
        return "delete-cuti";
//        KaryawanModel karyawan = existingCuti.getId_karyawan();
//        karyawan.setJumlahCuti(karyawan.getJumlahCuti()- existingCuti.getDurasi());
    }

    @RequestMapping(path = "/cuti/setujui")
    public String setujuiCuti(@RequestParam(value="id") Long idCuti, Model model){
        PengajuanCutiModel cuti = pengajuanCutiService.getCutiById(idCuti);
//        KaryawanModel karyawan = cuti.getId_karyawan();
//        karyawan.setJumlahCuti(karyawan.getJumlahCuti()+ cuti.getDurasi());
        pengajuanCutiService.setujuiCuti(cuti);
        return "redirect:/cuti";
    }

    @RequestMapping(path = "/cuti/tolak")
    public String tolakCuti(@RequestParam(value="id") Long idCuti, Model model){
        PengajuanCutiModel cuti = pengajuanCutiService.getCutiById(idCuti);
//        KaryawanModel karyawan = cuti.getId_karyawan();
//        karyawan.setJumlahCuti(karyawan.getJumlahCuti()+ cuti.getDurasi());
        pengajuanCutiService.tolakCuti(cuti);
        return "redirect:/cuti";
    }
}