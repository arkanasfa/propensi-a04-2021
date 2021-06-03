package propensi.a04.sisdi.controller;

import org.apache.tomcat.jni.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import propensi.a04.sisdi.model.*;
import propensi.a04.sisdi.repository.KaryawanDb;
import propensi.a04.sisdi.repository.StatusDB;
import propensi.a04.sisdi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    UserService userService;

    @Autowired
    StatusService statusService;

    @Autowired
    KaryawanDb karyawanDB;

    @Autowired
    StatusDB statusDB;

    @GetMapping("/tambah")
    private String addLemburForm(
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<KaryawanModel> karyawanList = karyawanDB.findAll();
        UserModel user = userService.findbyUsername(username);
        for(int i=0;i<karyawanList.size();i++){
            if(karyawanList.get(i).getId()==(user.getKaryawanModel().getId())){
                karyawanList.remove(i);
            }
        }
        model.addAttribute("lembur", new LemburModel());
        model.addAttribute("karyawanList",karyawanList);
        return "form-add-lembur";
    }

    @RequestMapping(value="/tambah", method = RequestMethod.POST)
    private String addLowonganSubmit(
            @ModelAttribute LemburModel lembur,
            @RequestParam(value="infalID") String infalID,
            HttpServletRequest request,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserModel user = userService.findbyUsername(username);
        lembur.setId_karyawan(user.getKaryawanModel());
        Date date = new Date();
        lembur.setTanggalRequest(date);
        if(lemburService.generateDurasi(lembur)<=0){
            String action = "dibuat";
            String reason = "Jam selesai lebih dulu dari jam mulai";
            model.addAttribute("action", action);
            model.addAttribute("reason", reason);
            return "notifikasi-gagal-lembur";
        }

        if(lembur.getTanggalLembur().before(lembur.getTanggalRequest())){
            String action = "dibuat";
            String reason = "Tanggal lembur lebih dulu dari tanggal hari ini";
            model.addAttribute("action", action);
            model.addAttribute("reason", reason);
            return "notifikasi-gagal-lembur";
        }
        if(lembur.getJenis()==1){
            lembur.setId_infal(null);
        }
        else if(lembur.getJenis()==2){
            Long idInfal = Long.valueOf(request.getParameter("infalID"));
            KaryawanModel id_karyawanInfal = karyawanDB.findById(idInfal).get();
            lembur.setId_infal(id_karyawanInfal);
        }
        String kode_lembur = lemburService.generateKodeLembur(lembur);
        lembur.setKode_lembur(kode_lembur);
        StatusModel id_status = statusService.getStatusById(Long.valueOf(1));
        lembur.setId_status(id_status);
        lemburService.addLembur(lembur);
        model.addAttribute("kode", lembur.getKode_lembur());
        return "add-lembur";
    }

    @GetMapping("/ubah")
    private String changeLemburForm(
            @RequestParam(value="id") Long id,
            Model model) {
        LemburModel lembur = lemburService.getLemburById(id);
        KaryawanModel karyawan = lembur.getId_karyawan();
        List<KaryawanModel> karyawanList = karyawanDB.findAll();
        for(int i=0;i<karyawanList.size();i++){
            if(karyawanList.get(i).getId()==(karyawan.getId())){
                karyawanList.remove(i);
            }
        }
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserModel user = userService.findbyUsername(username);
        lembur.setId_karyawan(user.getKaryawanModel());
        Date date = new Date();
        lembur.setTanggalRequest(date);
        if(lemburService.generateDurasi(lembur)<=0){
            String action = "diubah";
            String reason = "Jam selesai lebih dulu dari jam mulai";
            model.addAttribute("action", action);
            model.addAttribute("reason", reason);
            return "notifikasi-gagal-lembur";
        }
        if(lembur.getTanggalLembur().before(lembur.getTanggalRequest())){
            String action = "diubah";
            String reason = "Tanggal lembur lebih dulu dari tanggal hari ini";
            model.addAttribute("action", action);
            model.addAttribute("reason", reason);
            return "notifikasi-gagal-lembur";
        }
        if(lembur.getJenis()==1){
            lembur.setId_infal(null);
        }
        else if(lembur.getJenis()==2){
            Long idInfal = Long.valueOf(request.getParameter("infalID"));
            KaryawanModel id_karyawanInfal = karyawanDB.findById(idInfal).get();
            lembur.setId_infal(id_karyawanInfal);
        }
        String kode_lembur = lemburService.generateKodeLembur(lembur);
        lembur.setKode_lembur(kode_lembur);
        lemburService.changeLembur(lembur);
        model.addAttribute("kode", lembur.getKode_lembur());
        return "change-lembur";
    }

    @GetMapping("/detail")
    private String detailLembur(
            @RequestParam(value="id") Long id,
            Model model){
        LemburModel lembur = lemburService.getLemburById(id);
        Integer durasi = lemburService.generateDurasi(lembur);
        String menggantikan = "-";
        if(lembur.getId_infal()!=null){
            menggantikan = lembur.getId_infal().getKaryawan();
        }
        model.addAttribute("menggantikan",menggantikan);
        model.addAttribute("lembur",lembur);
        model.addAttribute("durasi",durasi);
        return "detail-lembur";
    }

    @GetMapping("/detail/verifikasi")
    private String detailLemburVerifikasi(
            @RequestParam(value="id") Long id,
            Model model){
        LemburModel lembur = lemburService.getLemburById(id);
        Integer durasi = lemburService.generateDurasi(lembur);
        String menggantikan = "-";
        if(lembur.getId_infal()!=null){
            menggantikan = lembur.getId_infal().getKaryawan();
        }
        model.addAttribute("menggantikan",menggantikan);
        model.addAttribute("lembur",lembur);
        model.addAttribute("durasi",durasi);
        return "detail-lembur-verifikasi";
    }

    @GetMapping("/verifikasi/terima")
    private String verifikasiLembur(
            @RequestParam(value="id") Long id,
            Model model){
        LemburModel lembur = lemburService.getLemburById(id);
        String kode = lembur.getKode_lembur();
        if(lembur.getId_status().getId()==1){
            lembur.setId_status(statusService.getStatusById(Long.valueOf(2)));
        }
        else if(lembur.getId_status().getId()==2){
            lembur.setId_status(statusService.getStatusById(Long.valueOf(3)));
        }
        else if(lembur.getId_status().getId()==3){
            lembur.setId_status(statusService.getStatusById(Long.valueOf(6)));
        }
        lemburService.changeLembur(lembur);
        model.addAttribute("kode",kode);
        model.addAttribute("status",lembur.getId_status().getStatus());
        return "verifikasi-lembur";
    }

    @GetMapping("/verifikasi/tolak")
    private String verifikasiLemburDitolak(
            @RequestParam(value="id") Long id,
            Model model){
        LemburModel lembur = lemburService.getLemburById(id);
        String kode = lembur.getKode_lembur();
        lembur.setId_status(statusService.getStatusById(Long.valueOf(5)));
        lemburService.changeLembur(lembur);
        model.addAttribute("kode",kode);
        model.addAttribute("status",lembur.getId_status().getStatus());
        return "verifikasi-lembur";
    }

    @GetMapping("/list")
    private String listLembur(
            Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<LemburModel> allListLembur = lemburService.getLemburList();
        List<LemburModel> listLembur = new ArrayList<LemburModel>();
        UserModel user = userService.findbyUsername(username);
        for(LemburModel lembur : allListLembur){
            if(lembur.getId_karyawan().getId()==user.getKaryawanModel().getId()){
                listLembur.add(lembur);
            }
        }
        System.out.println(user.getId_role().getId());
        model.addAttribute("listLembur",listLembur);
        model.addAttribute("user",user);
        return "list-lembur";
    }

    @GetMapping("/list/verifikasi")
    private String listVerifikasiLembur(
            Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserModel user = userService.findbyUsername(username);
        List<LemburModel> allListLembur = lemburService.getLemburList();
        List<LemburModel> listLembur = new ArrayList<LemburModel>();
        if(user.getId_role().getId()==6){
            for(LemburModel lembur : allListLembur){
                if(lembur.getId_status().getId()==1){
                    listLembur.add(lembur);
                }
            }
        }
        else if(user.getId_role().getId()==5){
            for(LemburModel lembur : allListLembur){
                if(lembur.getId_status().getId()==2){
                    listLembur.add(lembur);
                }
            }
        }
        else if(user.getId_role().getId()==7){
            for(LemburModel lembur : allListLembur){
                if(lembur.getId_status().getId()==3){
                    listLembur.add(lembur);
                }
            }
        }
        model.addAttribute("listLembur",listLembur);
        return "list-lembur-verifikasi";
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

