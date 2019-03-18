package com.juanlhiciano.app.controller;

import com.juanlhiciano.app.models.entity.User;
import com.juanlhiciano.app.models.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String loginAdmin(Model model) {
        User user = new User();
        model.addAttribute("title", "Entrar");
        model.addAttribute("user",user);
        return "admin_login";
    }

    //Login
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String login(@Valid User user, Model model, HttpSession session , RedirectAttributes flash){
        User u = userService.findByNameAndPassword(user.getName(),user.getPassword());
        
        List<User> users = userService.findAll();
        for(User usu : users) {
        	System.out.println(usu.getName());
        }
        
        if(u != null) {
            session.setAttribute("user_name",u.getName());
            return "logged/logged_home";
        }
        else{
            flash.addFlashAttribute("error", "Contraseña o usuario incorrecto");
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Model model, HttpSession session){
        if(session.getAttribute("user_name") == null){
            return "redirect:/";
        }
        else{
            model.addAttribute("name",session.getAttribute("user_name"));
            return "logged/logged_home";
        }
    }

}
