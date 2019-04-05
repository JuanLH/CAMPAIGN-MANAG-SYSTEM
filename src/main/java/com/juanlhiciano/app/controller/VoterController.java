package com.juanlhiciano.app.controller;

import com.juanlhiciano.app.models.entity.Padron2k20;
import com.juanlhiciano.app.models.entity.Voter;
import com.juanlhiciano.app.models.service.IPadron2k20Service;
import com.juanlhiciano.app.models.service.ISectorService;
import com.juanlhiciano.app.models.service.IVoterService;
import com.juanlhiciano.app.models.service.Padron2k20Service;
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
    
    @Autowired
    private IPadron2k20Service padron2k20Service;
    
    //3
    @RequestMapping(value="/nuevo_simpatizante")
    public String newVoter(@Valid Padron2k20 padron,Model model){
    	Voter citizen = new Voter();
    	citizen.setCedula(padron.getCedula());
    	citizen.setNames(padron.getNombres());
		citizen.setLastName1(padron.getApellido1());
		citizen.setLastName2(padron.getApellido2());
		citizen.setDob(padron.getFechanacimiento());
		citizen.setPlaceOfBirth(padron.getLugarnacimiento());
		//citizen.setIdCategoria(padron.getIdcategoria());
		citizen.setIdSexo(padron.getIdsexo());
		citizen.setEstadoCivil(padron.getIdestadocivil());
		//citizen.setIdOcupacion(padron.getIdocupacion());
		//citizen.setIdNacionalidad(padron.getIdnacionalidad());
		citizen.setIdMunicipio(padron.getIdmunicipio());
		citizen.setColegioElectoral(padron.getIdcolegio());
		citizen.setMunCed(padron.getMunCed());
		citizen.setSeqCed(padron.getSeqCed());
		citizen.setVerCed(padron.getVerCed());
		
		System.out.println("------------"+citizen.getNames());
    	model.addAttribute("title", "Unete");
    	model.addAttribute("citizen", citizen);
    	model.addAttribute("sectors", sectorService.findAll());
        return "new_voter";
    }
    
    //1
    @RequestMapping(value="/entrada_simpatizante")
    public String checkCedula(Model model){
    	model.addAttribute("title", "Unete");
    	model.addAttribute("simpatizante", new Padron2k20());
    	//model.addAttribute("sectors", sectorService.findAll());
        return "enter_cedula";
    }
    
    //2
    @RequestMapping(value="/nuevo_simpatizante",method = RequestMethod.POST)
    public String saveVoter(@Valid Padron2k20 cit,Model model) {
    	Padron2k20 padron = padron2k20Service.findByCedula(cit.getCedula());
    	
    	Voter citizen = new Voter();
    	if(padron != null) {
    		System.out.println(padron.getCedula()+"-"+padron.getNombres());
    		citizen.setCedula(padron.getCedula());
    		citizen.setNames(padron.getNombres());
    		citizen.setLastName1(padron.getApellido1());
    		citizen.setLastName2(padron.getApellido2());
    		citizen.setDob(padron.getFechanacimiento());
    		citizen.setPlaceOfBirth(padron.getLugarnacimiento());
    		citizen.setIdCategoria(padron.getIdcategoria());
    		citizen.setIdSexo(padron.getIdsexo());
    		citizen.setEstadoCivil(padron.getIdestadocivil());
    		citizen.setIdOcupacion(padron.getIdocupacion());
    		citizen.setIdNacionalidad(padron.getIdnacionalidad());
    		citizen.setIdMunicipio(padron.getIdmunicipio());
    		citizen.setColegioElectoral(padron.getIdcolegio());
    		citizen.setMunCed(padron.getMunCed());
    		citizen.setSeqCed(padron.getSeqCed());
    		citizen.setVerCed(padron.getVerCed());
    	}
    	model.addAttribute("title", "Unete");
    	model.addAttribute("citizen", citizen);
    	model.addAttribute("sectors", sectorService.findAll());
    	return "new_voter";
    }
    
    //4
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
