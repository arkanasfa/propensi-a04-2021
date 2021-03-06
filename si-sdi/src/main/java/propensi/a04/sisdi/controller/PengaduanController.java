package propensi.a04.sisdi.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import propensi.a04.sisdi.DTO.PengaduanDTOModel;
import propensi.a04.sisdi.DTO.SkorDto;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.PengaduanModel;
import propensi.a04.sisdi.model.StatusModel;
import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.KaryawanDb;
import propensi.a04.sisdi.repository.StatusDB;
import propensi.a04.sisdi.service.KaryawanService;
import propensi.a04.sisdi.service.OrangTuaService;
import propensi.a04.sisdi.service.PengaduanService;
import propensi.a04.sisdi.service.UserService;

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
    KaryawanService karyawanService;

    @Autowired
    StatusDB statusDB;

    @Autowired
    UserService userService;

    @Autowired
    OrangTuaService orangTuaService;

    @GetMapping("/add")
    public String addPengaduanFormPage(Model model){
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        model.addAttribute("listKaryawan",listKaryawan);
        model.addAttribute("pengaduan", new PengaduanModel());
        
        return "form-add-pengaduan";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addPengaduanSubmit(
        @ModelAttribute PengaduanDTOModel pengaduanDTO,
        Model model
    ){
        UserModel user = userService.getCurrentUser();
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
        
        StatusModel diajukan = statusDB.findById(Long.valueOf(1)).get();
        pengaduan.setId_status(diajukan);
        pengaduan.setId_user(user);

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

    @RequestMapping(value = "/viewall", method = RequestMethod.GET)
    public String listPengaduan(Model model, 
    @RequestParam("page") Optional<Integer> page, 
    @RequestParam("size")Optional<Integer> size){
        
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        UserModel user = userService.getCurrentUser();
        List<PengaduanModel> listPengaduan = user.getListPengaduan();

        Page<PengaduanModel> pengaduanPage = pengaduanService.findPaginated(PageRequest.of(currentPage-1, pageSize),listPengaduan);
        model.addAttribute("pengaduanPage", pengaduanPage);
        int totalPages = pengaduanPage.getTotalPages();
        if(totalPages>0){
            List<Integer> pageNumbers= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("listPengaduan",listPengaduan);
        return "viewall-pengaduan";
        
    }

    //mengelola
    @RequestMapping(value = "/view-all", method = RequestMethod.GET)
    public String listkasusPengaduan(Model model, 
    @RequestParam("page") Optional<Integer> page, 
    @RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        UserModel user = userService.getCurrentUser();
        if(user.getId_role().getId()==6){
            List<PengaduanModel> listPengaduan = pengaduanService.getPengaduanList();
            List<PengaduanModel> list = new ArrayList<PengaduanModel>();
        
            for (int i=0; i<listPengaduan.size();i++){
                if(listPengaduan.get(i).getId_status().getId().equals(Long.valueOf(1))){

                    list.add(listPengaduan.get(i));
                }
            }
            Page<PengaduanModel> pengaduanPage = pengaduanService.findPaginated(PageRequest.of(currentPage-1, pageSize),list);
            model.addAttribute("pengaduanPage", pengaduanPage);
            int totalPages = pengaduanPage.getTotalPages();
            if(totalPages>0){
                List<Integer> pageNumbers= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("list",list);
            return "view-all-pengaduan";
        }
        else if(user.getId_role().getId()==5){
            List<PengaduanModel> listPengaduan = pengaduanService.getPengaduanList();
            List<PengaduanModel> list = new ArrayList<PengaduanModel>();
        
            for (int i=0; i<listPengaduan.size();i++){
                if(listPengaduan.get(i).getId_status().getId().equals(Long.valueOf(8))){

                    list.add(listPengaduan.get(i));
                }
            }
            Page<PengaduanModel> pengaduanPage = pengaduanService.findPaginated(PageRequest.of(currentPage-1, pageSize),list);
            model.addAttribute("pengaduanPage", pengaduanPage);
            int totalPages = pengaduanPage.getTotalPages();
            if(totalPages>0){
                List<Integer> pageNumbers= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("list",list);
            return "view-all-pengaduan";
        }
            List<PengaduanModel> listPengaduan = pengaduanService.getPengaduanList();
            List<PengaduanModel> list = new ArrayList<PengaduanModel>();

            for (int i=0; i<listPengaduan.size();i++){
                if(listPengaduan.get(i).getId_status().getId().equals(Long.valueOf(9))){

                    list.add(listPengaduan.get(i));
                }
            }
            Page<PengaduanModel> pengaduanPage = pengaduanService.findPaginated(PageRequest.of(currentPage-1, pageSize),list);
            model.addAttribute("pengaduanPage", pengaduanPage);
            int totalPages = pengaduanPage.getTotalPages();
            if(totalPages>0){
                List<Integer> pageNumbers= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("list",list);
            return "view-all-pengaduan";
        
    }

    @GetMapping("/kelola/view")
    public String viewPengaduan(
        @RequestParam(value = "id") Long id,
        Model model
    ){  PengaduanModel pengaduan = pengaduanService.getPengaduanById(id);
        UserModel user = userService.getCurrentUser();
        if(user.getId_role().getId()==7){
            model.addAttribute("pengaduan", pengaduan);
        
            return "detail-pengaduan-sdi";
        }
        model.addAttribute("pengaduan", pengaduan);
        
        return "detail-pengaduan";
               
    }

    @GetMapping("/kelola/view/selesai")
    public String selesaikanPengaduan(
        @RequestParam(value = "id") Long id,
        Model model
    ){  
        PengaduanModel pengaduan = pengaduanService.getPengaduanById(id);
        
        StatusModel selesai = statusDB.findById(Long.valueOf(6)).get();
        pengaduan.setId_status(selesai);
        pengaduanService.updatePengaduan(pengaduan);
        String kode = pengaduan.getKode_pengaduan();

        SkorDto skorDto = new SkorDto();
        skorDto.setId(pengaduan.getId());
        skorDto.setSkor(0);
        skorDto.setNama(pengaduan.getNo_karyawan().getKaryawan());

        model.addAttribute("skorDto", skorDto);
        model.addAttribute("kode", kode);
        model.addAttribute("pengaduan", pengaduan);
        
        return "selesaikan-pengaduan";
               
    }

    @RequestMapping(value="/selesai", method = RequestMethod.POST)
    public String selesaikanPengaduanSubmit(
        @ModelAttribute SkorDto skorDto,
        Model model
    ){
        
        PengaduanModel pengaduan = pengaduanService.getPengaduanById(skorDto.getId());
        int skorPengaduan = pengaduan.getNo_karyawan().getSkorPengaduan() + skorDto.getSkor();
        pengaduan.getNo_karyawan().setSkorPengaduan(skorPengaduan);
        karyawanService.updateSkorPengaduan(pengaduan.getNo_karyawan());
        model.addAttribute("nama", pengaduan.getNo_karyawan().getKaryawan());
        model.addAttribute("score", skorDto.getSkor());
        
        return "selesai-pengaduan";
    }

    @GetMapping("/kelola/view/tolak")
    public String tolakPengaduan(
        @RequestParam(value = "id") Long id,
        Model model
    ){  PengaduanModel pengaduan = pengaduanService.getPengaduanById(id);
        StatusModel ditolak = statusDB.findById(Long.valueOf(5)).get();
        pengaduan.setId_status(ditolak);
        pengaduanService.updatePengaduan(pengaduan);
        String kode = pengaduan.getKode_pengaduan();
        model.addAttribute("kode", kode);
        
        return "tolak-pengaduan";
               
    }

    @GetMapping("/kelola/view/teruskan")
    public String teruskanPengaduan(
        @RequestParam(value = "id") Long id,
        Model model
    ){  PengaduanModel pengaduan = pengaduanService.getPengaduanById(id);
        UserModel user = userService.getCurrentUser();
        if(user.getId_role().getId()==6){
            StatusModel diteruskanOlehPU = statusDB.findById(Long.valueOf(8)).get();
            pengaduan.setId_status(diteruskanOlehPU);
            pengaduanService.updatePengaduan(pengaduan);
            String kode = pengaduan.getKode_pengaduan();
            model.addAttribute("kode", kode);
            
            return "teruskan-pengaduan";
        }
        else if(user.getId_role().getId()==5){
            StatusModel diteruskanOlehKabag = statusDB.findById(Long.valueOf(9)).get();
            pengaduan.setId_status(diteruskanOlehKabag);
            pengaduanService.updatePengaduan(pengaduan);
            String kode = pengaduan.getKode_pengaduan();
            model.addAttribute("kode", kode);
            
            return "teruskan-pengaduan";
        }
        StatusModel id_status = statusDB.findById(Long.valueOf(6)).get();
        pengaduan.setId_status(id_status);
        pengaduanService.updatePengaduan(pengaduan);
        String kode = pengaduan.getKode_pengaduan();
        model.addAttribute("kode", kode);
        
        return "teruskan-pengaduan";
               
    }

    @GetMapping("/delete")
    private String deletePengaduan(
            @RequestParam(value="id") Long id,
            Model model){
        PengaduanModel existingPengaduan = pengaduanService.getPengaduanById(id);
        String kode = existingPengaduan.getKode_pengaduan();
        pengaduanService.deletePengaduan(existingPengaduan);
        model.addAttribute("kode", kode);
        return "delete-pengaduan";
    }

}
