package propensi.a04.sisdi.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import propensi.a04.sisdi.DTO.PengaduanDTOModel;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.PengaduanModel;
import propensi.a04.sisdi.model.StatusModel;
import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.KaryawanDb;
import propensi.a04.sisdi.repository.StatusDB;
import propensi.a04.sisdi.service.PengaduanService;
import propensi.a04.sisdi.service.PengaduanServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/pengaduan")
public class PengaduanController {
    @Qualifier("pengaduanServiceImpl")
    @Autowired
    private PengaduanService pengaduanService;

    @Autowired
    KaryawanDb karyawanDB;

    @Autowired
    StatusDB statusDB;

    // @GetMapping("/")
    // private String home() {
    //     return "home";
    // }

    @GetMapping("/add")
    public String addPengaduanFormPage(Model model){
        List<KaryawanModel> listKaryawan = karyawanDB.findAll();
        model.addAttribute("listKaryawan",listKaryawan);
        model.addAttribute("pengaduan", new PengaduanModel());
        
        return "form-add-pengaduan";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addPengaduanSubmit(
        @ModelAttribute PengaduanDTOModel pengaduanDTO,
        @RequestParam("no_karyawan") String no_karyawan,
        HttpServletRequest request,
        Model model
    ){
        PengaduanModel pengaduan = new PengaduanModel();
        if (pengaduanDTO.getNo_karyawan()==null||pengaduanDTO.getDetailPengaduan()==""){
            return "add-pengaduan-gagal";
        }else if(pengaduanDTO.getDetailPengaduan()==""){
            return "add-pengaduan-gagal";
        }
        pengaduan.setDetailPengaduan(pengaduanDTO.getDetailPengaduan());

        pengaduan.setId(pengaduanDTO.getId());
        KaryawanModel karyawanNo = karyawanDB.findByNoKaryawan(pengaduanDTO.getNo_karyawan()).get();
        pengaduan.setNo_karyawan(karyawanNo);
        
        String kode_pengaduan = pengaduanService.generateKodePengaduan(pengaduan);
        pengaduan.setKode_pengaduan(kode_pengaduan);
        pengaduan.setTanggalPengaduan(LocalDate.now(ZoneId.of("Asia/Jakarta")));
        pengaduan.setStatusPengaduan(0);
        

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
    ){  PengaduanModel pengaduan = pengaduanService.getPengaduanById(id).get();    
        model.addAttribute("pengaduan", pengaduan);
        
        return "view-pengaduan";
               
    }

    @GetMapping("/viewall")
    public String listPengaduan(Model model) {
        List<PengaduanModel> listPengaduan = pengaduanService.getPengaduanList();
        model.addAttribute("listPengaduan",listPengaduan);
        
        //nanti ubah
        //UserModel user = userService.getCurrentUser();
        //List<PengaduanModel> listPengaduan = pengaduanService.getPengaduanByUser(user);
        //model.addAttribute("listPengaduan", listPengaduan );

        return "viewall-pengaduan";
    }

}
