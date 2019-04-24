package com.juanlhiciano.app.controller;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Padron2k20;
import com.juanlhiciano.app.models.entity.Sector;
import com.juanlhiciano.app.models.entity.Voter;
import com.juanlhiciano.app.models.service.ILeaderService;
import com.juanlhiciano.app.models.service.IPadron2k20Service;
import com.juanlhiciano.app.models.service.ISectorService;
import com.juanlhiciano.app.models.service.IVoterService;
import com.juanlhiciano.app.util.paginator.PageRender;
import com.juanlhiciano.app.util.recaptcha.ReCaptchaResponse;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @Autowired
    private ILeaderService leaderService;
    
   
    
    //1
    @RequestMapping(value="/entrada_simpatizante/{leaderCode}")
    public String newVoter(@PathVariable(value="leaderCode") String leaderCode,Model model){
    	model.addAttribute("title", "Unete");
    	model.addAttribute("padron2k20", new Padron2k20());
    	model.addAttribute("leaderCode", leaderCode);
        return "enter_cedula";
    }
    
    //1
    @RequestMapping(value="/entrada_simpatizante")
    public String newVoter(Model model){
    	model.addAttribute("title", "Unete");
    	model.addAttribute("padron2k20", new Padron2k20());
    	model.addAttribute("leaderCode", null);
        return "enter_cedula";
    }
    
    //2
    @RequestMapping(value="/nuevo_simpatizante/{leaderCode}",method = RequestMethod.POST)
    public String saveVoter(@Valid Padron2k20 simpatizante, BindingResult result,RedirectAttributes flash,@PathVariable(value="leaderCode") String leaderCode,Model model) {
    	Voter finded=null;
    	try {
    		finded = voterService.findById(simpatizante.getCedula());
    	}
    	catch(Exception e) {
    		System.out.println("Error al buscar por cedula ->"+e.getMessage());
    	}
    	if(finded != null && finded.getCedula() == simpatizante.getCedula()) {
    		FieldError f = new FieldError("cedula", "cedula", "La cedula "+simpatizante.getCedula()+" esta siendo usada por otro simpatizante");
    		result.addError(f);
    	}
    	
    	
    	if(result.hasErrors()) {
    		//model.addAttribute("title", "Unete");
        	model.addAttribute("padron2k20", simpatizante);
    		//flash.addFlashAttribute("warning","Cedula incorrecta");
    		return "enter_cedula";
    		//return (leaderCode != null)?"redirect:/voter/entrada_simpatizante/"+leaderCode:"redirect:/voter/entrada_simpatizante";
    	}
    	
    	Leader leader;
    	try {
    		leader = leaderService.findByCode(leaderCode);
    	}
    	catch(Exception e) {
    		System.out.println("No encontro leader");
    		leader = null;
    	}
    	Padron2k20 padron = padron2k20Service.findByCedula(simpatizante.getCedula());
    	Voter citizen = new Voter();
    	citizen.setCedula(simpatizante.getCedula());
    	citizen.setLeader(leader);
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
    	Leader leader = voter.getLeader();
    	System.out.println(voter.getCedula());
    	try {
    		voter = voterService.findById(voter.getCedula());
    	}
    	catch(Exception e) {
    		System.out.println("Exception ->"+e.getMessage());
    	}
    	voter.setEmail(email);
    	voter.setPhone(phone);
    	voter.setNecesidad(necesidad);
    	voter.setPensar(pensar);
    	voter.setSector(sector);
    	voter.setLeader(leader);
    	
    	Voter finded;
    	finded = voterService.findByPhone(voter.getPhone());
    	if(finded != null && finded.getCedula() != voter.getCedula()) {
    		FieldError f = new FieldError("phone", "phone", "El telefono "+voter.getPhone()+" esta siendo usado por otro simpatizante");
    		result.addError(f);
    	}
    	
    	finded = voterService.findByEmail(voter.getEmail());
    	if(finded != null && finded.getCedula() != voter.getCedula()) {
    		FieldError f = new FieldError("email", "email", "El correo "+voter.getEmail()+" esta siendo usado por otro simpatizante");
    		result.addError(f);
    	}
    	
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
			return (voter.getLeader()!=null)?"redirect:/voter/entrada_simpatizante/"+voter.getLeader().getCode()+"":"redirect:/voter/entrada_simpatizante";
			//return "redirect:/voter/entrada_simpatizante";
		} else {
			//message = "Please verify captcha";
			//return "redirect:/";
			voterService.save(voter);
			return (voter.getLeader()!=null)?"redirect:/voter/entrada_simpatizante/"+voter.getLeader().getCode()+"":"redirect:/voter/entrada_simpatizante";
		}
    }
    
    
    @RequestMapping(value="/listar-simpatizantes")
    public String listarSimpatizantes(@RequestParam(name="page", defaultValue = "0") int page, Model model) {
    	
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findAll(pageRequest);
    	
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listar-simpatizantes", voters);
    	model.addAttribute("title","Listar Simpatizante");
    	model.addAttribute("voters",voters);
    	model.addAttribute("page", pageRender);
    	return "logged/show_voters";
    }
    
    
    @RequestMapping(value="/{cedula}")
    public String seeVoter(@PathVariable(value="cedula")String cedula,Model model ,RedirectAttributes flash) {
    	Voter voter=null;
    	String message="";
    	
    	voter = voterService.findById(cedula);
    	if(voter == null) {
    		flash.addFlashAttribute("error", "La cedula no existe en la base de datos");
			return "redirect:/voter/listar-simpatizantes";
    	}
    	
    	model.addAttribute("title", "Ver simpatizante");
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("voter", voter);
    	model.addAttribute("leader", (voter.getLeader() != null)?voter.getLeader().toString():"NO TIENE");
    	return "logged/show_full_voter";
    }
    //Ver votantes por lugar form inicio
    @RequestMapping(value="/listarxlugar")
    public String byPlace(@RequestParam(name="page", defaultValue = "0") int page, Model model) {
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findAll(pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxlugar", voters);
    	
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", new Sector());
    	model.addAttribute("voters",voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	return "logged/show_votersByPlace";
    }
    
    //Ver votantes por lugar form button
    @RequestMapping(value="/listarxlugar",method = RequestMethod.POST)
    public String byPlace(@RequestParam(name="page", defaultValue = "0") int page,@RequestParam int id, Model model) {
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findBySector(sectorService.findById(id),pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxlugar", voters);
    	
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", new Sector());
    	model.addAttribute("voters",voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	return "redirect:/voter/listarxlugar/"+id;
    }
    
  //Ver votantes por lugar form button group place
    @RequestMapping(value="/listarxlugar/{sectorId}",method = RequestMethod.GET)
    public String byPlace2(@RequestParam(name="page", defaultValue = "0") int page, @PathVariable(value="sectorId")int sectorId, Model model) {
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findBySector(sectorService.findById(sectorId),pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxlugar/"+sectorId, voters);
    	
    	Sector sector = new Sector();
    	sector.setId(sectorId);
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", sector);
    	model.addAttribute("voters",voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	return "logged/show_votersByPlace";
    }
    
  //Ver votantes por leader form inicio
    @RequestMapping(value="/listarxdirigente")
    public String byLeader(@RequestParam(name="page", defaultValue = "0") int page, Model model) {
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findAll(pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxdirigente", voters);
    	
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("leader", new Leader());
    	model.addAttribute("voters",voters);
    	model.addAttribute("leaders", leaderService.findAll());
    	model.addAttribute("page", pageRender);
    	return "logged/show_votersByLeader";
    }
    
    //Ver votantes por leader form button
    @RequestMapping(value="/listarxdirigente",method = RequestMethod.POST)
    public String byLeader(@RequestParam(name="page", defaultValue = "0") int page,@RequestParam int id, Model model) {
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findBySector(sectorService.findById(id),pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxdirigente", voters);
    	
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", new Sector());
    	model.addAttribute("voters",voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	return "redirect:/voter/listarxdirigente/"+id;
    }
    
  //Ver votantes por leader form button group place
    @RequestMapping(value="/listarxdirigente/{leaderId}",method = RequestMethod.GET)
    public String byLeader2(@RequestParam(name="page", defaultValue = "0") int page, @PathVariable(value="leaderId")long leaderId, Model model) {
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findByLeader(leaderService.findById(leaderId),pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxdirigente/"+leaderId, voters);
    	
    	Leader leader = new Leader();
    	leader.setId(leaderId);
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("leader", leader);
    	model.addAttribute("voters",voters);
    	model.addAttribute("leaders", leaderService.findAll());
    	model.addAttribute("page", pageRender);
    	return "logged/show_votersByLeader";
    }
    
}
