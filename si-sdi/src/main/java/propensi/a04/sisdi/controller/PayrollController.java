package propensi.a04.sisdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import propensi.a04.sisdi.model.DokumenTotalModel;
import propensi.a04.sisdi.model.GajiModel;
import propensi.a04.sisdi.model.KomponenPengaliModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/payroll")
public class PayrollController {
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    LemburService lemburService;
//
//    @Autowired
//    GajiService gajiService;

    @GetMapping("")
    private String requestPayrollForm(Model model) {
        model.addAttribute("dokumenTotal", new DokumenTotalModel());
        return "mengajukan-payroll";
    }

    @GetMapping("/detailPayroll")
    private String detailPayroll(Model model) {
        model.addAttribute("detailPayroll", new GajiModel());
        return "detail-payroll";
    }

    @GetMapping("/detailGaji")
    private String detailGaji(Model model){
        model.addAttribute("detailGaji", new GajiModel());
        return "detail-gaji";
    }

    @GetMapping("/komponenGaji")
    private String komponenGaji(Model model){
        model.addAttribute("komponenGaji", new KomponenPengaliModel());
        return"komponen-pengali-gaji";
    }
}
