package apap.tutorial.cineplux.controller;
import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.model.RoleModel;
import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.service.RoleService;
import apap.tutorial.cineplux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    private String addUserFormPage(Model model) {
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.getListRole();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }
    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }
    @GetMapping("/viewall")
    private String listUser(Model model) {
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "viewall-user";
    }
    @GetMapping("/updatePassword/{id}")
    private String updatePasswordForm(
            @PathVariable String id
    ) {
        UserModel myUser = userService.findUserById(id);
        return "form-change-password";
    }

    @PostMapping("/updatePassword")
    private String updatePasswordSubmit(
            @ModelAttribute UserModel userModel,
            String newPass,
            String confirmPass,
            Model model
    ) {
        UserModel myUser = userService.findUserByUsername(userModel.getUsername());
        if (userService.isMatch(userModel.getPassword(), myUser.getPassword())){
            if (newPass.equals(confirmPass)){
                userService.setPassword(myUser, newPass);
                userService.addUser(myUser);
                model.addAttribute("mess", "Password berhasil diupdate");
            }
            else {
                model.addAttribute("mess", "Password tidak sama, silakan coba ulang.");
            }
        }
        else {
            model.addAttribute("mess", "Password salah!");
        }
        model.addAttribute("username", userModel.getUsername());
        return "change-password";
    }
    @GetMapping( "/delete/{id}")
    public String removeUser(
            @PathVariable String id,
            Model model
    ) {
        UserModel user = userService.findUserById(id);

        if (user == null) {
            model.addAttribute("mess", "User tidak ditemukan");
        }
        else {
            userService.deleteUser(user);
            model.addAttribute("mess", "User berhasil dihapus");
        }
        return "delete-user";
    }
}