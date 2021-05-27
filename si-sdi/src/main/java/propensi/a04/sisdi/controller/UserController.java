package propensi.a04.sisdi.controller;

import org.apache.tomcat.jni.User;
import propensi.a04.sisdi.model.*;
import propensi.a04.sisdi.repository.UserDb;
import propensi.a04.sisdi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private UserDb userDb;

    @GetMapping("/addUser")
    private String addLemburForm(
            Model model) {
        List<RoleModel> roleList = roleService.findAll();
        model.addAttribute("user", new UserModel());
        model.addAttribute("roleList",roleList);
        return "form-add-user";
    }

    @RequestMapping(value ="/addUser", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute UserModel user, @RequestParam("roleID") String roleID, Model model){
        user.setId_role(roleService.findRolebyId(Long.valueOf(roleID)));
        userService.addUser(user);
        model.addAttribute("user", user);
        return "add-user";
    }

    @GetMapping("/addUser/Informasi")
    private String addDetailUser(
            @RequestParam(value="username") String username,
            Model model) {
        UserModel user = userService.findbyUsername(username);
        if(user.getId_role().getId()==1){
            model.addAttribute("orangtua", new OrangTuaModel());
            model.addAttribute("user", user);
            return "form-user-orangtua";
        }
        else if(user.getId_role().getId()==2){
            model.addAttribute("siswa", new SiswaModel());
            model.addAttribute("user", user);
            return "form-user-siswa";
        }
        model.addAttribute("karyawan", new KaryawanModel());
        model.addAttribute("user", user);
        return "form-user-karyawan";
    }

    @RequestMapping(value ="/addUser/InformasiKaryawan", method = RequestMethod.POST)
    public String addDetailUserSubmit(@ModelAttribute KaryawanModel karyawan,
                                      @RequestParam(value="username") String username,
                                      Model model){
        UserModel user = userService.findbyUsername(username);
        karyawan.setId_user(user);
        karyawanService.addKaryawan(karyawan);
        model.addAttribute("user", user);
        return "add-informasi-user";
    }
}
