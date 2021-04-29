package propensi.a04.sisdi.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import propensi.a04.sisdi.DTO.PengaduanDTOModel;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.PengaduanModel;
import propensi.a04.sisdi.model.StatusModel;
import propensi.a04.sisdi.repository.KaryawanDb;
import propensi.a04.sisdi.repository.StatusDB;
import propensi.a04.sisdi.service.PengaduanService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/pengaduan")
public class PengaduanController {
    @Qualifier("pengaduanServiceImpl")
    @Autowired
    PengaduanService pengaduanService;

    @Autowired
    KaryawanDb karyawanDb;

    @Autowired
    StatusDB statusDB;

    @GetMapping("/add")
    public String addPengaduanFormPage(Model model){
        List<KaryawanModel> listKaryawan = karyawanDb.findAll();
        model.addAttribute("listKaryawan",listKaryawan);
        model.addAttribute("pengaduan", new PengaduanModel());
        
        return "form-add-pengaduan";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addPengaduanSubmit(
        @ModelAttribute PengaduanDTOModel pengaduanDTO,
        Model model
    ){

        PengaduanModel pengaduan = new PengaduanModel();
        if (pengaduanDTO.getNo_karyawan()==null||pengaduanDTO.getDetailPengaduan()==""){
            return "add-pengaduan-gagal";
        }else if(pengaduanDTO.getDetailPengaduan()==""){
            return "add-pengaduan-gagal";
        }
        pengaduan.setDetailPengaduan(pengaduanDTO.getDetailPengaduan());

        KaryawanModel karyawanNo = karyawanDb.findByNoKaryawan(pengaduanDTO.getNo_karyawan()).get();
        pengaduan.setNo_karyawan(karyawanNo);
        
        String kode_pengaduan = pengaduanService.generateKodePengaduan(pengaduan);
        pengaduan.setKode_pengaduan(kode_pengaduan);
        pengaduan.setTanggalPengaduan(LocalDate.now(ZoneId.of("Asia/Jakarta")));
        
        StatusModel id_status = statusDB.findById(Long.valueOf(1)).get();
        pengaduan.setId_status(id_status);
        

        PengaduanModel tmpPengaduan = pengaduanService.addPengaduan(pengaduan); 
        tmpPengaduan.setKode_pengaduan(pengaduanService.generateKodePengaduan(tmpPengaduan));
        pengaduanService.addPengaduan(tmpPengaduan);
        model.addAttribute("kode", pengaduan.getKode_pengaduan());

        return "add-pengaduan";
    }

    @GetMapping("/view")
    public String viewDetailPengaduan(
        @RequestParam(value = "id") Long id,
        Model model
    ){  PengaduanModel pengaduan = pengaduanService.getPengaduanById(id);    
        model.addAttribute("pengaduan", pengaduan);
        
        return "view-pengaduan";
               
    }

    @GetMapping("/viewall")
    public String listPengaduan(Model model) {
        List<PengaduanModel> listPengaduan = pengaduanService.getPengaduanList();
        model.addAttribute("listPengaduan",listPengaduan);

        return "viewall-pengaduan";
    }

}
