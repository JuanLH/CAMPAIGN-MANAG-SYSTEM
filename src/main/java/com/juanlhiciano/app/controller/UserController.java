package com.juanlhiciano.app.controller;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.User;
import com.juanlhiciano.app.models.service.ILeaderService;
import com.juanlhiciano.app.models.service.IUserService;
import com.juanlhiciano.app.models.service.IUserTypeService;

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
    
    @Autowired
    IUserTypeService userTypeService;
    
    @Autowired 
    ILeaderService leaderService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String loginAdmin(Model model) {
        User user = new User();
        model.addAttribute("title", "Entrar");
        model.addAttribute("userTypes", userTypeService.findAll());
        
        model.addAttribute("user",user);
        return "admin_login";
    }

    //Login
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String login( User user, Model model, HttpSession session , RedirectAttributes flash){
        User u=null;
        Leader l=null;
    	
		u = userService.findByCedulaAndPassword(user.getCedula(),user.getPassword());
    	l = leaderService.findByCedulaAndPassword(user.getCedula(),user.getPassword());
    	
        
        //Finded
        if(u != null || l != null) {
        	
        	if(u != null) {
        		session.setAttribute("user_type",1);
	            session.setAttribute("user_name",u.getName());
	            session.setAttribute("user_cedula",u.getCedula());//para validar acceso
	            return "redirect:/voter/listar-simpatizantes";//logged/logged_home";
        	}
        	else {
        		session.setAttribute("user_type",0);
        		session.setAttribute("user_code",l.getCode());
        		session.setAttribute("user_cedula",l.getCedula());//para validar acceso
        		return "redirect:/leader/listar-simpatizantes";//"leader_logged/logged_home";
        	}
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
