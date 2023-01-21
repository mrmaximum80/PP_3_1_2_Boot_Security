package springboot.app.controller;

import org.springframework.transaction.annotation.Transactional;
import springboot.app.model.Role;
import springboot.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springboot.app.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


    @GetMapping("/add")
    public String addUserPage(ModelMap model) {
        // Дефолтный password
        User user = userService.createUserPassword(new User());
        //-------------------------------------------
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.getAllRoles());
        model.addAttribute("userroles", new ArrayList<String>());
        return "input-user";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("user") User user) {
        // Добаляем роли
        User userWithRoles = userService.addUserRoles(user);
        //--------------------------------------
        userService.saveUser(userWithRoles);
        return "redirect:/admin";
    }

    @PostMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/update/{id}")
    public String updateUserPage(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", userService.getAllRoles());
        model.addAttribute("userroles", new ArrayList<String>());
        return "update-user";
    }

    @PatchMapping ("/update-user")
    public String updateUser(@ModelAttribute("user") User user) {
        // Добаляем роли
        User userWithRoles = userService.addUserRoles(user);
        //--------------------------------------
        userService.saveUser(userWithRoles);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserPage(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/look/{id}")
    public String lookUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

}
