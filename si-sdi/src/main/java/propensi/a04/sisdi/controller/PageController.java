package propensi.a04.sisdi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.UserDb;
import propensi.a04.sisdi.service.LemburService;
import propensi.a04.sisdi.service.UserService;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home() {
        UserModel user = userService.getCurrentUser();
        if(user.getId_role().getId()==6 || user.getId_role().getId()==5 || user.getId_role().getId()==7){
            return "home-approval";
        }
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}
