package propensi.a04.sisdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import propensi.a04.sisdi.model.*;
import propensi.a04.sisdi.repository.UserDb;
import propensi.a04.sisdi.service.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class PageController {
    @Autowired
    PayrollService payrollService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private OrangTuaService orangTuaService;

    @Autowired
    private SiswaService siswaService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(
            Model model) {
        String nama = payrollService.findUser().getUsername();
        String role = payrollService.findUser().getId_role().getRole();
        Long idKaryawan;
        try{
            KaryawanModel karyawan = payrollService.findUser().getKaryawanModel();
            idKaryawan= karyawan.getId();
        }catch(NullPointerException e){
            idKaryawan=(long)0;
        }
        model.addAttribute("idKaryawan", idKaryawan);
        model.addAttribute("nama", nama);
        model.addAttribute("role", role);
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/profile")
    public String profile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usern = auth.getName();
        UserModel user1 = userService.findbyUsername(usern);
        if(user1.getId_role().getId().equals(Long.valueOf(1))){
            OrangTuaModel ortu = orangTuaService.getByIduser(user1);
            model.addAttribute("user1", ortu);
        }else if(user1.getId_role().getId().equals(Long.valueOf(2))){
            SiswaModel siswa = siswaService.getByIduser(user1);
            model.addAttribute("user2", siswa);
        } else {
            KaryawanModel karyawan = karyawanService.getByIduser(user1);
            model.addAttribute("user3", karyawan);
            int cuti = karyawan.getJumlahCuti()*100/12;
            int lembur = karyawan.getJumlahLembur()*100/10;
            int absensi = karyawan.getJumlahAbsensi()*100/10;
            int pengaduan = karyawan.getSkorPengaduan();
            int performa = karyawan.getSkorPerforma();
            model.addAttribute("cuti", cuti);
            model.addAttribute("lembur", lembur);
            model.addAttribute("absensi", absensi);
            model.addAttribute("pengaduan", pengaduan);
            model.addAttribute("performa", performa);
        }
        model.addAttribute("user", user1);
        return "profilee";
    }

}
