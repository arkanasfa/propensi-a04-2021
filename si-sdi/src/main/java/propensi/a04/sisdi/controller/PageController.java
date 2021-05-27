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
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}
