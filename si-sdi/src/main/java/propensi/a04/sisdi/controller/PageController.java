package propensi.a04.sisdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import propensi.a04.sisdi.model.DokumenTotalModel;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.UserDb;
import propensi.a04.sisdi.service.KaryawanService;
import propensi.a04.sisdi.service.LemburService;
import propensi.a04.sisdi.service.PayrollService;
import propensi.a04.sisdi.service.UserService;

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
    KaryawanService karyawanService;


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


}
