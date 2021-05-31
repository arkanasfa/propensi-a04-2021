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

import java.util.ArrayList;
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

    @RequestMapping("/cuti/kelola")
    public String viewAllCutiKelola(Model model) {
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

        if (roleName.equals("Pimpinan Unit")){
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
        return "view-all-cuti-kelola";
    }

    @RequestMapping("/cuti")
    public String viewAllCuti(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usern = auth.getName();
        UserModel user1 = userService.findbyUsername(usern);
        KaryawanModel karyawan = karyawanService.getByIduser(user1);
        int sisaCuti = 12-karyawan.getJumlahCuti();
        List<PengajuanCutiModel> newList = karyawan.getListPengajuanCuti();
        model.addAttribute("sisaCuti", sisaCuti);
        model.addAttribute("cutiList", newList);
        return "view-all-cuti";
    }

    @RequestMapping(path = "/cuti/detail")
    public String detailCuti(
            @RequestParam(value="id") Long id, Model model){
        PengajuanCutiModel cuti = pengajuanCutiService.getCutiById(id);
        model.addAttribute("cuti", cuti);
        return "detail-cuti";
    }

    @RequestMapping(path = "/cuti/detail/kelola")
    public String detailCutiKelola(
            @RequestParam(value="id") Long id, Model model){
        PengajuanCutiModel cuti = pengajuanCutiService.getCutiById(id);
        model.addAttribute("cuti", cuti);
        return "detail-cuti-kelola";
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
        KaryawanModel karyawan = karyawanService.getKaryawanbyId(user1.getId());
        cuti.setId_karyawan(karyawan);
            try {
                String kodeCuti = pengajuanCutiService.generateKodeCuti(cuti);
                cuti.setKode_cuti(kodeCuti);
                StatusModel id_status = statusService.getStatusById(Long.valueOf(1));
                cuti.setIdstatus(id_status);
                Date date = new Date();
                cuti.setTanggalRequest(date);
                int durasi = pengajuanCutiService.generateDurasi(cuti);
                int gap = pengajuanCutiService.generateStartValid(cuti);

                StatusModel diajukan = statusService.getStatusById(Long.valueOf(1));
                StatusModel accPU = statusService.getStatusById(Long.valueOf(2));
                StatusModel accKB = statusService.getStatusById(Long.valueOf(3));

                List<PengajuanCutiModel> listCutiKaryawan = karyawan.getListPengajuanCuti();
                for (int i=0;i<listCutiKaryawan.size();i++){
                    if(!listCutiKaryawan.get(i).getIdstatus().equals(diajukan) && !listCutiKaryawan.get(i).getIdstatus().equals(accPU) && !listCutiKaryawan.get(i).getIdstatus().equals(accKB)){
                        listCutiKaryawan.remove(listCutiKaryawan.get(i));
                    }
                }

                int durasi2 = 0;
                for (int i=0; i<listCutiKaryawan.size();i++){
                    durasi2 = durasi2 + listCutiKaryawan.get(i).getDurasi();
                    if(listCutiKaryawan.get(i).getId()== cuti.getId()){
                        durasi2 = durasi2 - listCutiKaryawan.get(i).getDurasi();
                    }
                }

                if (durasi>0 && gap <= 0 && (durasi+karyawan.getJumlahCuti()+durasi2<13)) {
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
        KaryawanModel karyawan = karyawanService.getByIduser(user1);
        cuti.setId_karyawan(karyawan);
        try {
            String kodeCuti = pengajuanCutiService.generateKodeCuti(cuti);
            cuti.setKode_cuti(kodeCuti);
            int durasi = pengajuanCutiService.generateDurasi(cuti);
            int gap = pengajuanCutiService.generateStartValid(cuti);

            StatusModel diajukan = statusService.getStatusById(Long.valueOf(1));
            StatusModel accPU = statusService.getStatusById(Long.valueOf(2));
            StatusModel accKB = statusService.getStatusById(Long.valueOf(3));

            List<PengajuanCutiModel> listCutiKaryawan = karyawan.getListPengajuanCuti();
            for (int i=0;i<listCutiKaryawan.size();i++){
                if(!listCutiKaryawan.get(i).getIdstatus().equals(diajukan) && !listCutiKaryawan.get(i).getIdstatus().equals(accPU) && !listCutiKaryawan.get(i).getIdstatus().equals(accKB)){
                    listCutiKaryawan.remove(listCutiKaryawan.get(i));
                }
            }

            int durasi2 = 0;
            for (int i=0; i<listCutiKaryawan.size();i++){
                durasi2 = durasi2 + listCutiKaryawan.get(i).getDurasi();
                if(listCutiKaryawan.get(i).getId()== cuti.getId()){
                    durasi2 = durasi2 - listCutiKaryawan.get(i).getDurasi();
                }
            }

            if (durasi>0 && gap <= 0 && (durasi+karyawan.getJumlahCuti()+durasi2<13)) {
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
    }

    @RequestMapping(path = "/cuti/delete")
    public String deleteCuti(@RequestParam(value="id") Long idCuti, Model model){
        PengajuanCutiModel existingCuti = pengajuanCutiService.getCutiById(idCuti);
        String kodeCuti = existingCuti.getKode_cuti();
        if (existingCuti.getIdstatus().getId()==7){
            pengajuanCutiService.batalkanCuti(existingCuti);
            model.addAttribute("kode_cuti", kodeCuti);
            return "batalkan-cuti";
        }
        else{
            pengajuanCutiService.deleteCuti(existingCuti);
            model.addAttribute("kode_cuti", kodeCuti);
            return "delete-cuti";
        }
    }

    @RequestMapping(path = "/cuti/setujui")
    public String setujuiCuti(@RequestParam(value="id") Long idCuti, Model model){
        PengajuanCutiModel cuti = pengajuanCutiService.getCutiById(idCuti);
        if(cuti.getIdstatus().getId()==3) {
            KaryawanModel karyawan = cuti.getId_karyawan();
            karyawan.setJumlahCuti(karyawan.getJumlahCuti()+ cuti.getDurasi());
        }
        if(cuti.getIdstatus().getId()==16) {
            KaryawanModel karyawan = cuti.getId_karyawan();
            karyawan.setJumlahCuti(karyawan.getJumlahCuti()- cuti.getDurasi());
        }
        pengajuanCutiService.setujuiCuti(cuti);

        return "redirect:/cuti/kelola";
    }

    @RequestMapping(path = "/cuti/tolak")
    public String tolakCuti(@RequestParam(value="id") Long idCuti, Model model){
        PengajuanCutiModel cuti = pengajuanCutiService.getCutiById(idCuti);
        if(cuti.getIdstatus().getId()==10 || cuti.getIdstatus().getId()==11 || cuti.getIdstatus().getId()==16) {
            pengajuanCutiService.tolakPembatalanCuti(cuti);
        }
        else {
            pengajuanCutiService.tolakCuti(cuti);
        }
        return "redirect:/cuti/kelola";
    }
}