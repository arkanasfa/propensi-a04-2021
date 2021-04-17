package propensi.a04.sisdi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import propensi.a04.sisdi.model.PengaduanModel;
import propensi.a04.sisdi.service.PengaduanService;
import propensi.a04.sisdi.service.PengaduanServiceImpl;

@Controller
public class PengaduanController {
    @Qualifier("pengaduanServiceImpl")
    @Autowired
    private PengaduanService pengaduanService;

    // @GetMapping("/")
    // private String home() {
    //     return "home";
    // }

    @GetMapping("/pengaduan/add")
    public String addPengaduanFormPage(Model model){
        model.addAttribute("pengaduan", new PengaduanModel());
        
        return "form-add-pengaduan";
    }

    @PostMapping("/pengaduan/add")
    public String addPengaduanSubmit(
        @ModelAttribute PengaduanModel pengaduan,
        Model model
    ){
        pengaduanService.addPengaduan(pengaduan);
        model.addAttribute("id", pengaduan.getId());

        return "add-pengaduan";
    }

    @GetMapping("/pengaduan/view")
    public String viewDetailPengaduan(
        @RequestParam(value = "id") Long id,
        Model model
    ){  PengaduanModel pengaduan = pengaduanService.getPengaduanById(id).get();    
        model.addAttribute("pengaduan", pengaduan);
        
        return "view-pengaduan";
               
    }

    @GetMapping("/pengaduan/viewall")
    public String listResep(Model model) {
        List<PengaduanModel> listPengaduan = pengaduanService.getPengaduanList();
        model.addAttribute("listPengaduan", listPengaduan );

        return "viewall-pengaduan";
    }

}
