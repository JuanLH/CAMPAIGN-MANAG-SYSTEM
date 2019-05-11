package com.juanlhiciano.app.controller;

import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Sector;
import com.juanlhiciano.app.models.entity.User;
import com.juanlhiciano.app.models.entity.Voter;
import com.juanlhiciano.app.models.service.ILeaderService;
import com.juanlhiciano.app.models.service.ISectorService;
import com.juanlhiciano.app.models.service.IUserService;
import com.juanlhiciano.app.models.service.IVoterService;
import com.juanlhiciano.app.util.paginator.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/leader")
public class LeaderController {

    @Autowired
    ILeaderService leaderService;
    
    @Autowired
    IVoterService voterService;
    
    @Autowired
    ISectorService sectorService;
    
    @Autowired
    IUserService userService;

    @RequestMapping(value="test")
    public @ResponseBody List<Leader> getAll(){
        return leaderService.findAll();

    }

    @RequestMapping(value="/nuevo-dirigente")
    public String newLeader(Model model){
    	model.addAttribute("title", "Crear Dirigente");
    	model.addAttribute("leader", new Leader());
        return "logged/new_leader";
    }
    
    @RequestMapping(value="/save_leader",method = RequestMethod.POST)
    public String saveLeader(@Valid Leader leader, BindingResult result, RedirectAttributes flash,Model model) {

    	Leader finded;
    	User finded2;
    	
    	//valido en ambas entidades si existe la cedula ya que el login es compartido y no pueden estar duplicados.
    	finded = leaderService.findByCedula(leader.getCedula());
    	finded2= userService.findByCedula(leader.getCedula());
    	if(finded != null  || finded2 != null ) {
    		FieldError f = new FieldError("cedula", "cedula", "La cedula "+leader.getCedula()+" esta siendo usado por otra persona");
    		result.addError(f);
    	}
    	
    	finded = leaderService.findByCode(leader.getCode());
    	if(finded != null && finded.getId() != leader.getId()) {
    		FieldError f = new FieldError("code", "code", "El codigo "+leader.getCode()+" esta siendo usado por otro dirigente");
    		result.addError(f);
    	}
    	
    	finded = leaderService.findByEmail(leader.getEmail());
    	if( finded != null && finded.getId() != leader.getId()) {
    		FieldError f = new FieldError("email", "email", "El Email "+leader.getEmail()+" esta siendo usado por otro dirigente");
    		result.addError(f);
    	}
    	
    	finded = leaderService.findByPhone(leader.getPhone());
    	if(finded!= null && finded.getId() != leader.getId()) {
    		FieldError f = new FieldError("phone", "phone", "El Telefono "+leader.getPhone()+" esta siendo usado por otro dirigente");
    		result.addError(f);
    	}
    	
    	if(result.hasErrors()) {
    		//model.addAttribute("title", "Crear Dirigente");
    		System.out.println("Error Count = "+result.getErrorCount());
    		
			for(ObjectError obj: result.getAllErrors()) {
				System.out.println(obj.getDefaultMessage()+" * "+obj.toString()+" --- "+obj.getObjectName());
			}
			
			return "logged/new_leader";
    	}
    	
    	String mensajeFlash = (leader.getId() != null)? "Dirigente editado con exito" : "Dirigente creado con exito";
    	leaderService.save(leader);
    	//status.setComplete();
    	//flash.addFlashAttribute("success", mensajeFlash);
    	return "redirect:/home";
    	
    }
    
    @RequestMapping(value="/{id}")
	public String editar(@PathVariable(value="id")Long id,Model model ,RedirectAttributes flash) {
    	Leader leader = null;
    	
    	if(id > 0) {
			leader = leaderService.findById(id);
			if(leader == null) {
				flash.addFlashAttribute("error", "El ID del dirigente no existe en la BBDD!");
				return "redirect:/listar-dirigentes";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del dirigente no puede ser cero!");
			return "redirect:/listar-dirigentes";
		}
    	
    	model.addAttribute("leader", leader);
    	model.addAttribute("title", "Editar Dirigente");
    	return "logged/new_leader";
	}

    @RequestMapping(value="/listar-dirigentes")
    public String listarDirigentes(@RequestParam(name="page", defaultValue = "0") int page, Model model) {
    	
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Leader> leaders = leaderService.findAll(pageRequest);
    	
    	PageRender<Leader> pageRender = new PageRender<>("/leader/listar-dirigentes", leaders);
    	model.addAttribute("title","Listar Dirigentes");
    	model.addAttribute("leaders",leaders);
    	model.addAttribute("page", pageRender);
    	return "logged/show_leaders";
    }
    
    @RequestMapping(value="/listar-simpatizantes")
    public String listarSimpatizantes(@RequestParam(name="page", defaultValue = "0") int page,HttpSession session, Model model) {
    	
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findByLeader(leaderService.findByCode(session.getAttribute("user_code").toString()), pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/voter/listar-simpatizantes", voters);
    	model.addAttribute("title","Listar Simpatizante");
    	model.addAttribute("voters",voters);
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "leader_logged/show_voters";
    }
    
  //Ver votantes por lugar form inicio
    @RequestMapping(value="/listarxlugar")
    public String byPlace(@RequestParam(name="page", defaultValue = "0") int page,HttpSession session, Model model) {
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findByLeaderAndSector(leaderService.findByCode(session.getAttribute("user_code").toString()), sectorService.findById(1), pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/leader/listarxlugar", voters);
    	
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", new Sector());
    	model.addAttribute("voters",voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "leader_logged/show_votersByPlace";
    }
    
    //Ver votantes por lugar form button
    @RequestMapping(value="/listarxlugar",method = RequestMethod.POST)
    public String byPlace(@RequestParam(name="page", defaultValue = "0") int page,HttpSession session,@RequestParam int id, Model model) {
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findByLeaderAndSector(leaderService.findByCode(session.getAttribute("user_code").toString()), sectorService.findById(1), pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/leader/listarxlugar", voters);
    	
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", new Sector());
    	model.addAttribute("voters",voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "redirect:/leader/listarxlugar/"+id;
    }
    
  //Ver votantes por lugar form button group place
    @RequestMapping(value="/listarxlugar/{sectorId}",method = RequestMethod.GET)
    public String byPlace2(@RequestParam(name="page", defaultValue = "0") int page,HttpSession session, @PathVariable(value="sectorId")int sectorId, Model model) {
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Voter> voters = voterService.findByLeaderAndSector(leaderService.findByCode(session.getAttribute("user_code").toString()), sectorService.findById(sectorId), pageRequest);
    	PageRender<Voter> pageRender = new PageRender<>("/leader/listarxlugar/"+sectorId, voters);
    	
    	Sector sector = new Sector();
    	sector.setId(sectorId);
    	model.addAttribute("title", "Buscar simpatizantes");
    	model.addAttribute("sector", sector);
    	model.addAttribute("voters", voters);
    	model.addAttribute("sectors", sectorService.findAll());
    	model.addAttribute("page", pageRender);
    	model.addAttribute("total_consult", voters.getTotalElements());
    	return "leader_logged/show_votersByPlace";
    }
    
}
