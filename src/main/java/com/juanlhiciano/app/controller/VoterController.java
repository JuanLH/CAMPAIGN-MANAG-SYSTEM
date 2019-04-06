package com.juanlhiciano.app.controller;

import com.juanlhiciano.app.models.entity.Padron2k20;
import com.juanlhiciano.app.models.entity.Sector;
import com.juanlhiciano.app.models.entity.Voter;
import com.juanlhiciano.app.models.service.IPadron2k20Service;
import com.juanlhiciano.app.models.service.ISectorService;
import com.juanlhiciano.app.models.service.IVoterService;
import com.juanlhiciano.app.util.recaptcha.ReCaptchaResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
   
    
    //1
    @RequestMapping(value="/entrada_simpatizante")
    public String newVoter(Model model){
    	model.addAttribute("title", "Unete");
    	model.addAttribute("simpatizante", new Padron2k20());
        return "enter_cedula";
    }
    
    //2
    @RequestMapping(value="/nuevo_simpatizante",method = RequestMethod.POST)
    public String saveVoter(@Valid Padron2k20 cit,BindingResult result,RedirectAttributes flash,Model model) {
    	if(result.hasErrors()) {
    		model.addAttribute("title", "Unete");
        	model.addAttribute("simpatizante", cit);
    		flash.addFlashAttribute("warning","Cedula incorrecta");
    		return "redirect:/voter/entrada_simpatizante";
    		//return "redirect:/voter/entrada_simpatizante";
    	}
    	
    	Padron2k20 padron = padron2k20Service.findByCedula(cit.getCedula());
    	Voter citizen = new Voter();
    	citizen.setCedula(cit.getCedula());
    	if(padron != null) {
    		System.out.println(padron.getCedula()+"-"+padron.getNombres());
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
    		citizen.setIdMunicipio(Integer.parseInt(padron.getIdmunicipio()));
    		citizen.setColegioElectoral(padron.getIdcolegio());
    		citizen.setMunCed(padron.getMunCed());
    		citizen.setSeqCed(padron.getSeqCed());
    		citizen.setVerCed(padron.getVerCed());
    		voterService.save(citizen);
    	}
    
    	model.addAttribute("title", "Unete");
    	model.addAttribute("voter", citizen);
    	model.addAttribute("sectors", sectorService.findAll());
    	boolean disable = (citizen.getNames() != null)? true: false;
    	model.addAttribute("disable", disable);
    	return "new_voter";
    }
    
    //3
    @PostMapping(value="/save_voter")
    public String saveVoter(@Valid Voter voter,BindingResult result,@RequestParam(name="g-recaptcha-response") String captchaResponse,Model model) {
    	//Para poder actualizar el registro
    	String email = voter.getEmail();
    	String phone = voter.getPhone();
    	String necesidad = voter.getNecesidad();
    	String pensar = voter.getPensar();
    	Sector sector = voter.getSector();
    	voter = voterService.findById(voter.getCedula());
    	voter.setEmail(email);
    	voter.setPhone(phone);
    	voter.setNecesidad(necesidad);
    	voter.setPensar(pensar);
    	voter.setSector(sector);
    	
    	String url = "https://www.google.com/recaptcha/api/siteverify";
		String params = "?secret=6LchAJgUAAAAALGyxvElCD8XE7R_KKccuYv7tZ3-&response="+captchaResponse;
		
		if(result.hasErrors()) {
			System.out.println("Error Count = "+result.getErrorCount());
			
			for(ObjectError obj: result.getAllErrors()) {
				System.out.println(obj.getDefaultMessage()+" * "+obj.toString()+" --- "+obj.getObjectName());
			}
			
			//model.addAttribute("voter", voter);
			model.addAttribute("sectors", sectorService.findAll());
			//boolean disable = (voter.getNames() != null)? true: false;
			//model.addAttribute("disable", disable);
    		return "new_voter";
    	}
		
		ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
		if(reCaptchaResponse.isSuccess()) {
			
			voterService.save(voter);
			return "redirect:/voter/entrada_simpatizante";
		} else {
			//message = "Please verify captcha";
			//return "redirect:/";
			voterService.save(voter);
			return "redirect:/voter/entrada_simpatizante";
		}
    }
    
    
}
