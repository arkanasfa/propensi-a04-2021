package propensi.a04.sisdi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import propensi.a04.sisdi.model.DokumenTotalModel;
import propensi.a04.sisdi.model.GajiModel;
import propensi.a04.sisdi.model.KomponenPengaliModel;
import propensi.a04.sisdi.repository.KomponenPengaliDb;
import propensi.a04.sisdi.service.PayrollService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/payroll")
public class PayrollController {
    @Autowired
    PayrollService payrollService;

    @Autowired
    KomponenPengaliDb komponenPengaliDb;

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

    // View Komponen Gaji
    @GetMapping("/komponenGaji")
    private String komponenGaji(Model model){
        KomponenPengaliModel komponenGaji = payrollService.getKomponenGajiById(Long.parseLong("1"));
        model.addAttribute("komponenGaji", komponenGaji);
        return"komponen-pengali-gaji";
    }

    // Form Ubah Komponen Gaji
    @GetMapping("/komponenGaji/ubah")
    private String ubahKomponenGajiForm(
            @RequestParam(value="id") Long id,
            Model model) {
        KomponenPengaliModel komponenGaji = payrollService.getKomponenGajiById(id);
        model.addAttribute("komponenGaji", komponenGaji);
        return "komponen-pengali-gaji-form";
    }

    // Notifikasi Ubah Komponen Gaji
    @RequestMapping(value="/komponenGaji/ubah", method = RequestMethod.POST)
    private String ubahKomponenGajiSubmit(
            @ModelAttribute KomponenPengaliModel komponenGaji,
            Model model) {
        payrollService.ubahKomponenGaji(komponenGaji);
        model.addAttribute("komponenGaji", komponenGaji);
        model.addAttribute("kode", "");
        return "payroll-notifs";
    }
}
