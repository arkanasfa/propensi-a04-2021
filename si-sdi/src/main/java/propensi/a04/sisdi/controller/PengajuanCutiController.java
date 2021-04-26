package propensi.a04.sisdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.PengajuanCutiModel;

import propensi.a04.sisdi.model.StatusModel;
import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.StatusDB;
import propensi.a04.sisdi.service.PengajuanCutiService;
import propensi.a04.sisdi.service.KaryawanService;

import java.util.Date;
import java.util.List;

@Controller
public class PengajuanCutiController {
    @Autowired
    PengajuanCutiService pengajuanCutiService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    StatusDB statusDb;

    @RequestMapping("/cuti")
    public String viewAllCuti(Model model, UserModel user){
        KaryawanModel karyawan = karyawanService.getByUser(user);
        int sisaCuti = 12-karyawan.getJumlahCuti();
        List<PengajuanCutiModel> cutiList = pengajuanCutiService.getCutiList();
        model.addAttribute("sisaCuti", sisaCuti);
        model.addAttribute("cutiList", cutiList);
        return "view-all-cuti";
    }

    @RequestMapping(path = "/cuti/detail")
    public String detailCuti(
        @RequestParam(value="id") Long id, Model model){
        PengajuanCutiModel cuti = pengajuanCutiService.getCutiById(id).orElse(null);
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
        KaryawanModel karyawan = karyawanService.getByUser(user);
        if(karyawan.getJumlahCuti()+cuti.getDurasi()<=12){
            cuti.setId_karyawan(karyawan);
            String kodeCuti = pengajuanCutiService.generateKodeCuti(cuti);
            cuti.setKode_cuti(kodeCuti);
            StatusModel id_status = statusDb.findById(Long.valueOf(1)).get();
            cuti.setId_status(id_status);
            Date date = new Date();
            cuti.setTanggalRequest(date);
            pengajuanCutiService.addCuti(cuti);
            model.addAttribute("kode_cuti", cuti.getKode_cuti());
            return "add-cuti";
        }
        else {
            return "view-all-cuti";
        }
    }

    @RequestMapping(path = "/cuti/edit")
    public String editCutiForm(@RequestParam(value="id") Long id, Model model){
        PengajuanCutiModel existingCuti = pengajuanCutiService.getCutiById(id).orElse(null);
        model.addAttribute("cuti", existingCuti);
        return "form-change-cuti";
    }

    @RequestMapping(path = "/cuti/edit", method = RequestMethod.POST)
    public String editCutiSubmit(@ModelAttribute PengajuanCutiModel cuti, Model model, UserModel user){
        KaryawanModel karyawan = karyawanService.getByUser(user);
        if(karyawan.getJumlahCuti()+cuti.getDurasi()<=12) {
            cuti.setId_karyawan(karyawan);
            String kodeCuti = pengajuanCutiService.generateKodeCuti(cuti);
            model.addAttribute("kode_cuti", cuti.getKode_cuti());
            return "edit-cuti";
        }
        else {
            return "view-all-cuti";
        }
    }

    @RequestMapping(path = "/cuti/delete")
    public String editCuti(@RequestParam(value="id") Long idCuti, Model model){
        PengajuanCutiModel existingCuti = pengajuanCutiService.getCutiById(idCuti).orElse(null);
        String kodeCuti = existingCuti.getKode_cuti();
        KaryawanModel karyawan = existingCuti.getId_karyawan();
        karyawan.setJumlahCuti(karyawan.getJumlahCuti()- existingCuti.getDurasi());
        pengajuanCutiService.deleteCuti(existingCuti);
        model.addAttribute("kode_cuti", kodeCuti);
        return "delete-cuti";
    }
}
