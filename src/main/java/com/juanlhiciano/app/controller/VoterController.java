package com.juanlhiciano.app.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Padron2020;
import com.juanlhiciano.app.models.entity.Sector;
import com.juanlhiciano.app.models.entity.Voter;
import com.juanlhiciano.app.models.service.ILeaderService;
import com.juanlhiciano.app.models.service.IPadron2020Service;
import com.juanlhiciano.app.models.service.ISectorService;
import com.juanlhiciano.app.models.service.IUserService;
import com.juanlhiciano.app.models.service.IVoterService;
import com.juanlhiciano.app.util.paginator.PageRender;
import com.juanlhiciano.app.util.recaptcha.ReCaptchaResponse;

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
    private IPadron2020Service Padron2020Service;
    
    @Autowired
    private ILeaderService leaderService;
    
    @Autowired
    private IUserService userService;
    
   
    
    //1
    @RequestMapping(value="/entrada_simpatizante/{leaderCode}")
    public String newVoter(@PathVariable(value="leaderCode") String leaderCode,Model model){
    	model.addAttribute("title", "Unete");
    	model.addAttribute("simpatizante", new Padron2020());
    	model.addAttribute("leaderCode", leaderCode);
        return "enter_cedula";
    }
    
    //1
    @RequestMapping(value="/entrada_simpatizante")
    public String newVoter(Model model){
    	model.addAttribute("title", "Unete");
    	model.addAttribute("simpatizante", new Padron2020());
    	model.addAttribute("leaderCode", null);
        return "enter_cedula";
    }
    
    
    //2
    @RequestMapping(value="/nuevo_simpatizante/{leaderCode}",method = RequestMethod.POST)
    public String saveVoter(@Valid Padron2020 simpatizante, BindingResult result,RedirectAttributes flash,@PathVariable(value="leaderCode") String leaderCode,Model model) {
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
    		
    		model.addAttribute("title", "Unete");
        	model.addAttribute("simpatizante", simpatizante);
        	flash.addFlashAttribute("warning","La cedula "+simpatizante.getCedula()+" esta siendo usada por otro simpatizante");
        	return (leaderCode != null)?"redirect:/voter/entrada_simpatizante/"+leaderCode:"redirect:/voter/entrada_simpatizante";
    	}
    	
    	
    	if(result.hasErrors()) {
    		model.addAttribute("title", "Unete");
        	model.addAttribute("simpatizante", simpatizante);
    		flash.addFlashAttribute("warning","Cedula incorrecta, solo 11 numeros");
    		return (leaderCode != null)?"redirect:/voter/entrada_simpatizante/"+leaderCode:"redirect:/voter/entrada_simpatizante";
    	}
    	
    	Leader leader;
    	try {
    		leader = leaderService.findByCode(leaderCode);
    	}
    	catch(Exception e) {
    		System.out.println("No encontro leader");
    		leader = null;
    	}
    	Padron2020 padron = Padron2020Service.findByCedula(simpatizante.getCedula());
    	Voter citizen = new Voter();
    	citizen.setCedula(simpatizante.getCedula());
    	citizen.setLeader(leader);
    	if(padron != null) {
    		System.out.println(padron.getCedula()+"-"+padron.getNombres());
    		citizen.setNames(padron.getNombres());
    		citizen.setLastName1(padron.getApellido1());
    		citizen.setLastName2(padron.getApellido2());
    		citizen.setDob((padron.getFechaNacimiento().equals(""))?null:padron.getFechaNacimiento());
    		citizen.setPlaceOfBirth(padron.getLugarNacimiento());
    		citizen.setCategoria(padron.getIDCategoria());
    		citizen.setIdSexo(padron.getIdSexo());
    		citizen.setEstadoCivil(padron.getIdEstadoCivil());
    		citizen.setOcupacion(padron.getIdOcupacion());
    		citizen.setNacionalidad(padron.getIDNacionalidad());
    		citizen.setMunicipio(padron.getIDMunicipio());
    		citizen.setColegioElectoral(padron.getIDColegio());
    		citizen.setMunCed(padron.getMunCed());
    		citizen.setSeqCed(padron.getSeqCed());
    		citizen.setVerCed(padron.getVerCed());
    		//voterService.save(citizen);
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
    public String saveVoter(@Valid Voter voter,BindingResult result,RedirectAttributes flash,@RequestParam(name="g-recaptcha-response") String captchaResponse,Model model,HttpServletRequest request) {
    	//Para poder actualizar el registro
    	String email = voter.getEmail();
    	String phone = voter.getPhone();
    	String necesidad = voter.getNecesidad();
    	String pensar = voter.getPensar();
    	Sector sector = voter.getSector();
    	Leader leader = voter.getLeader();
    	Date dob =  (Date) voter.getDob();
    	System.out.println(voter.getCedula());
    	try {
    		voter = voterService.findById(voter.getCedula());
    	}
    	catch(Exception e) {
    		System.out.println("Exception ->"+e.getMessage());
    	}
    	voter.setDob(dob);
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
		
		
		String url = "https://www.google.com/recaptcha/api/siteverify";
		String params = "?secret=6LchAJgUAAAAALGyxvElCD8XE7R_KKccuYv7tZ3-&response="+captchaResponse;
		
		ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
		
		
		if(reCaptchaResponse.isSuccess()) {
			voterService.save(voter);
			return "redirect:/";
		} else {
			flash.addFlashAttribute("error", "Verifique el Capcha");
			//return "redirect:"+request.getHeader("Referer");
			FieldError f = new FieldError("verCed", "verCed", "Verifique el capcha");
    		result.addError(f);
			//voterService.save(voter);
			//return (voter.getLeader()!=null)?"redirect:/voter/entrada_simpatizante/"+voter.getLeader().getCode()+"":"redirect:/voter/entrada_simpatizante";
    		return "new_voter";
		}
		
    }
    
    
    @RequestMapping(value="/nuevo_simpatizante/{leaderCode}",method = RequestMethod.GET)
    public String refresh_form(Model model){
    	return "redirect:/voter/entrada_simpatizante";
    }
    
    
    @RequestMapping(value="/listar-simpatizantes")
    public String listarSimpatizantes(@RequestParam(name="page", defaultValue = "0") int page,HttpSession session, Model model) {
    	
    	
    	
    	if(session.getAttribute("user_cedula")==null)
    		return "redirect:/";
    	 else if(userService.findByCedula(session.getAttribute("user_cedula").toString())== null)
    		return "redirect:/";
    		
    	
    	
    	
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findAll(pageRequest);
    	
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listar-simpatizantes", voters);
    	model.addAttribute("title","Listar Simpatizante");
    	model.addAttribute("voters",voters);
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "logged/show_voters";
    }
    
    
    @RequestMapping(value="/{cedula}")
    public String seeVoter(@PathVariable(value="cedula")String cedula,Model model ,HttpSession session,RedirectAttributes flash) {
    	if(session.getAttribute("user_cedula")==null)
    		return "redirect:/";
    	 else if(userService.findByCedula(session.getAttribute("user_cedula").toString())== null)
    		return "redirect:/";
    	
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
    
    @RequestMapping(value="/4leader/{cedula}")
    public String seeVoter4Leader(@PathVariable(value="cedula")String cedula,Model model ,RedirectAttributes flash) {
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
    	return "leader_logged/show_full_voter";
    }
    //Ver votantes por lugar form inicio
    @RequestMapping(value="/listarxlugar")
    public String byPlace(@RequestParam(name="page", defaultValue = "0") int page,HttpSession session, Model model) {
    	if(session.getAttribute("user_cedula")==null)
    		return "redirect:/";
    	 else if(userService.findByCedula(session.getAttribute("user_cedula").toString())== null)
    		return "redirect:/";
    	
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findBySector(sectorService.findById(1),pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxlugar", voters);
    	
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", new Sector());
    	model.addAttribute("voters",voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "logged/show_votersByPlace";
    }
    
    //Ver votantes por lugar form button
    @RequestMapping(value="/listarxlugar",method = RequestMethod.POST)
    public String byPlace(@RequestParam(name="page", defaultValue = "0") int page,@RequestParam int id,HttpSession session, Model model) {
    	if(session.getAttribute("user_cedula")==null)
    		return "redirect:/";
    	 else if(userService.findByCedula(session.getAttribute("user_cedula").toString())== null)
    		return "redirect:/";
    	
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findBySector(sectorService.findById(id),pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxlugar", voters);
    	
    	/*model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", new Sector());
    	model.addAttribute("voters",voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());*/
    	return "redirect:/voter/listarxlugar/"+id;
    }
    
  //Ver votantes por lugar form button group place
    @RequestMapping(value="/listarxlugar/{sectorId}",method = RequestMethod.GET)
    public String byPlace2(@RequestParam(name="page", defaultValue = "0") int page, @PathVariable(value="sectorId")int sectorId, HttpSession session, Model model) {
    	if(session.getAttribute("user_cedula")==null)
    		return "redirect:/";
    	 else if(userService.findByCedula(session.getAttribute("user_cedula").toString())== null)
    		return "redirect:/";
    	
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
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "logged/show_votersByPlace";
    }
    
  //Ver votantes por leader form inicio
    @RequestMapping(value="/listarxdirigente")
    public String byLeader(@RequestParam(name="page", defaultValue = "0") int page,HttpSession session, Model model) {
    	if(session.getAttribute("user_cedula")==null)
    		return "redirect:/";
    	 else if(userService.findByCedula(session.getAttribute("user_cedula").toString())== null)
    		return "redirect:/";
    	
    	
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findByLeader(leaderService.findById(1L),pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxdirigente", voters);
    	
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("leader", new Leader());
    	model.addAttribute("voters",voters);
    	model.addAttribute("leaders", leaderService.findAll());
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "logged/show_votersByLeader";
    }
    
    //Ver votantes por leader form button
    @RequestMapping(value="/listarxdirigente",method = RequestMethod.POST)
    public String byLeader(@RequestParam(name="page", defaultValue = "0") int page,@RequestParam int id,HttpSession session, Model model) {
    	if(session.getAttribute("user_cedula")==null)
    		return "redirect:/";
    	 else if(userService.findByCedula(session.getAttribute("user_cedula").toString())== null)
    		return "redirect:/";
    	
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findBySector(sectorService.findById(id),pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxdirigente", voters);
    	
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", new Sector());
    	model.addAttribute("voters",voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "redirect:/voter/listarxdirigente/"+id;
    }
    
  //Ver votantes por leader form button group place
    @RequestMapping(value="/listarxdirigente/{leaderId}",method = RequestMethod.GET)
    public String byLeader2(@RequestParam(name="page", defaultValue = "0") int page, @PathVariable(value="leaderId") long leaderId,HttpSession session, Model model) {
    	if(session.getAttribute("user_cedula")==null)
    		return "redirect:/";
    	 else if(userService.findByCedula(session.getAttribute("user_cedula").toString())== null)
    		return "redirect:/";
    	
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
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "logged/show_votersByLeader";
    }
    
    @RequestMapping(value="/listarxindependiente")
    public String listarSimpatizantesInd(@RequestParam(name="page", defaultValue = "0") int page,HttpSession session, Model model) {
    	if(session.getAttribute("user_cedula")==null)
    		return "redirect:/";
    	 else if(userService.findByCedula(session.getAttribute("user_cedula").toString())== null)
    		return "redirect:/";
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findByLeader(null,pageRequest);
    	
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxindependiente", voters);
    	model.addAttribute("title","Listar Simpatizante");
    	model.addAttribute("voters",voters);
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "logged/show_voters";
    }
    
    @RequestMapping(value="/listarxmarca")
    public String listarSimpatizantesCheck(@RequestParam(name="page", defaultValue = "0") int page, Model model) {
    	
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findByCheck(true,pageRequest);
    	
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listarxmarca", voters);
    	model.addAttribute("title","Listar Simpatizante");
    	model.addAttribute("voters",voters);
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "logged/show_voters";
    }
    
    @PostMapping(value="/mark")
    @ResponseBody
    public String mark_voter(@RequestParam String cedula,Model model ,RedirectAttributes flash) {
    	Voter voter=null;
    	String message="";
    	voter = voterService.findById(cedula);
    	
    	if(voter == null) {
    		flash.addFlashAttribute("error", "La cedula no existe en la base de datos");
			return "redirect:/voter/listar-simpatizantes";
    	}
    	
    	voter.setCheck((short)1);
    	try {
    		voterService.save(voter);
    		message = "Se agrego correctamente";
    	}
    	catch(Exception e) {
    		message = "No se pudo agregar";
    	}
    	
    	
    	return message;
    }
    
    @PostMapping(value="/unmark")
    @ResponseBody
    public String unmark_voter(@RequestParam String cedula,Model model ,RedirectAttributes flash) {
    	Voter voter=null;
    	String message="";
    	voter = voterService.findById(cedula);
    	
    	if(voter == null) {
    		flash.addFlashAttribute("error", "La cedula no existe en la base de datos");
			return "redirect:/voter/listar-simpatizantes";
    	}
    	
    	voter.setCheck((short)0);
    	try {
    		voterService.save(voter);
    		message = "Se quito correctamente";
    	}
    	catch(Exception e) {
    		message = "No se pudo quitar";
    	}
    	
    	
    	return message;
    }
}
