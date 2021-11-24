package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String home(@ModelAttribute UserModel user, Model model) {
        model.addAttribute("idUser", user.getId());
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
