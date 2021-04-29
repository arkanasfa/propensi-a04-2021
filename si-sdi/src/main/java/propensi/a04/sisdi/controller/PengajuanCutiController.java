package propensi.a04.sisdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import propensi.a04.sisdi.model.*;

import propensi.a04.sisdi.repository.KaryawanDb;
import propensi.a04.sisdi.repository.PengajuanCutiDb;
import propensi.a04.sisdi.repository.StatusDB;
import propensi.a04.sisdi.service.PengajuanCutiService;
import propensi.a04.sisdi.service.KaryawanService;
import propensi.a04.sisdi.service.StatusService;

import java.util.Date;
import java.util.List;

@Controller
public class PengajuanCutiController {
    @Autowired
    PengajuanCutiService pengajuanCutiService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    StatusService statusService;

    @Autowired
    PengajuanCutiDb pengajuanCutiDb;

    @Autowired
    KaryawanDb karyawanDb;

    @Autowired
    StatusDB statusDb;

    @RequestMapping("/cuti")
    public String viewAllCuti(Model model, UserModel user){
//        KaryawanModel karyawan = karyawanService.getByUser(user);
//        int sisaCuti = 12-karyawan.getJumlahCuti();
        List<PengajuanCutiModel> cutiList = pengajuanCutiService.getCutiList();
//        model.addAttribute("sisaCuti", sisaCuti);
        model.addAttribute("cutiList", cutiList);
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
    public String addCutiSubmit(@ModelAttribute PengajuanCutiModel cuti, Model model, UserModel user){
//        KaryawanModel karyawan = karyawanService.getByUser(user);
//        if(karyawan.getJumlahCuti()+cuti.getDurasi()<=12){
//            cuti.setId_karyawan(karyawan);
            try {
//                KaryawanModel id_karyawan = karyawanDb.findById(Long.valueOf(3)).get();
//                cuti.setId_karyawan(id_karyawan);
                String kodeCuti = pengajuanCutiService.generateKodeCuti(cuti);
                cuti.setKode_cuti(kodeCuti);
                StatusModel id_status = statusService.getStatusById(Long.valueOf(1));
                cuti.setId_status(id_status);
                Date date = new Date();
                cuti.setTanggalRequest(date);
                int durasi = pengajuanCutiService.generateDurasi(cuti);
                if(durasi>0 && durasi<13) {
                    cuti.setDurasi(durasi);
                    pengajuanCutiService.addCuti(cuti);
                    model.addAttribute("kode_cuti", cuti.getKode_cuti());
                    return "add-cuti";
                }
                else {
                    return "notifikasi-gagal-durasi-cuti";
                }
            }
            catch (NullPointerException nullException){
                return "notifikasi-gagal-add-cuti";
            }
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
    public String editCutiSubmit(@ModelAttribute PengajuanCutiModel cuti, Model model, UserModel user){
//        KaryawanModel karyawan = karyawanService.getByUser(user);
//        if(karyawan.getJumlahCuti()+cuti.getDurasi()<=12) {
//            cuti.setId_karyawan(karyawan);
        try {
            String kodeCuti = pengajuanCutiService.generateKodeCuti(cuti);
            cuti.setKode_cuti(kodeCuti);
            int durasi = pengajuanCutiService.generateDurasi(cuti);
            if (durasi > 0 && durasi < 13) {
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
//        KaryawanModel karyawan = existingCuti.getId_karyawan();
//        karyawan.setJumlahCuti(karyawan.getJumlahCuti()- existingCuti.getDurasi());
        pengajuanCutiService.deleteCuti(existingCuti);
        model.addAttribute("kode_cuti", kodeCuti);
        return "delete-cuti";
    }
}