package propensi.a04.sisdi.controller;

import propensi.a04.sisdi.model.*;
import propensi.a04.sisdi.repository.KaryawanDB;
import propensi.a04.sisdi.repository.StatusDB;
import propensi.a04.sisdi.service.*;
import propensi.a04.sisdi.repository.LemburDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/lembur")
public class LemburController {
    @Autowired
    LemburService lemburService;

    @Autowired
    KaryawanDB karyawanDB;

    @Autowired
    StatusDB statusDB;

    @GetMapping("/tambah")
    private String addLemburForm(
            Model model) {
        List<KaryawanModel> karyawanList = karyawanDB.findAll();
        model.addAttribute("lembur", new LemburModel());
        model.addAttribute("karyawanList",karyawanList);
        return "form-add-lembur";
    }

    @RequestMapping(value="/tambah", method = RequestMethod.POST)
    private String addLowonganSubmit(
            @ModelAttribute LemburModel lembur,
            @RequestParam("infalID") String infalID,
            HttpServletRequest request,
            Model model) {
        KaryawanModel id_karyawan = karyawanDB.findById(Long.valueOf(3)).get();
        lembur.setId_karyawan(id_karyawan);
        if (infalID.equals("NULL")==false) {
            Long idInfal = Long.valueOf(request.getParameter("infalID"));
            KaryawanModel id_karyawanInfal = karyawanDB.findById(idInfal).get();
            lembur.setId_infal(id_karyawanInfal);
        }
        String kode_lembur = lemburService.generateKodeLembur(lembur);
        lembur.setKode_lembur(kode_lembur);
        StatusModel id_status = statusDB.findById(Long.valueOf(1)).get();
        lembur.setId_status(id_status);
        Date date = new Date();
        lembur.setTanggalRequest(date);
        lemburService.addLembur(lembur);
        model.addAttribute("kode", lembur.getKode_lembur());
        return "add-lembur";
    }

    @GetMapping("/ubah")
    private String changeLemburForm(
            @RequestParam(value="id") Long id,
            Model model) {
        LemburModel lembur = lemburService.getLemburById(id);
        List<KaryawanModel> karyawanList = karyawanDB.findAll();
        model.addAttribute("lembur", lembur);
        model.addAttribute("karyawanList",karyawanList);
        return "form-change-lembur";
    }

    @RequestMapping(value="/ubah", method = RequestMethod.POST)
    private String changeLowonganSubmit(
            @ModelAttribute LemburModel lembur,
            @RequestParam("infalID") String infalID,
            HttpServletRequest request,
            Model model) {
        KaryawanModel id_karyawan = karyawanDB.findById(Long.valueOf(3)).get();
        lembur.setId_karyawan(id_karyawan);
        if (infalID.equals("NULL")==false) {
            Long idInfal = Long.valueOf(request.getParameter("infalID"));
            KaryawanModel id_karyawanInfal = karyawanDB.findById(idInfal).get();
            lembur.setId_infal(id_karyawanInfal);
        }
        if (infalID.equals("NULL")==true) {
            lembur.setId_infal(null);
        }
        String kode_lembur = lemburService.generateKodeLembur(lembur);
        lembur.setKode_lembur(kode_lembur);
        lemburService.changeLembur(lembur);
        model.addAttribute("kode", lembur.getKode_lembur());
        return "change-lembur";
    }

    @GetMapping("/detail")
    private String detailLowongan(
            @RequestParam(value="id") Long id,
            Model model){
        LemburModel lembur = lemburService.getLemburById(id);
        Integer durasi = lemburService.generateDurasi(lembur);
        model.addAttribute("lembur",lembur);
        model.addAttribute("durasi",durasi);
        return "detail-lembur";
    }

    @GetMapping("/list")
    private String listLembur(
            Model model){
        List<LemburModel> listLembur = lemburService.getLemburList();
        model.addAttribute("listLembur",listLembur);
        return "list-lembur";
    }

    @GetMapping("/delete")
    private String deleteLembur(
            @RequestParam(value="id") Long id,
            Model model){
        LemburModel lembur = lemburService.getLemburById(id);
        String kode = lembur.getKode_lembur();
        lemburService.deleteLembur(lembur);
        model.addAttribute("kode",kode);
        return "delete-lembur";
    }

}

