package com.juanlhiciano.app.controller;




import com.juanlhiciano.app.models.entity.Voter;
import com.juanlhiciano.app.models.service.ISectorService;
import com.juanlhiciano.app.models.service.IVoterService;
import com.juanlhiciano.app.util.recaptcha.ReCaptchaResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    IVoterService voterService;
    
    @Autowired
    ISectorService sectorService;
    
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value="/nuevo-simpatizante")
    public String newVoter(Model model){
    	model.addAttribute("title", "Unete");
    	model.addAttribute("voter", new Voter());
    	model.addAttribute("sectors", sectorService.findAll());
        return "new_voter";
    }
    
    @RequestMapping(value="/save_voter",method = RequestMethod.POST)
    public String saveVoter(@Valid Voter voter,@RequestParam(name="g-recaptcha-response") String captchaResponse) {
    	
    	String url = "https://www.google.com/recaptcha/api/siteverify";
		String params = "?secret=6LchAJgUAAAAALGyxvElCD8XE7R_KKccuYv7tZ3-&response="+captchaResponse;
		
		ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
		if(reCaptchaResponse.isSuccess()) {
			voterService.save(voter);
			return "redirect:/voter";
		} else {
			//message = "Please verify captcha";
			return "redirect:/";
}
    }
    
    
}
