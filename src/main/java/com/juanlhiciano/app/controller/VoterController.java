package com.juanlhiciano.app.controller;

import com.google.gson.Gson;
import com.juanlhiciano.app.models.entity.Voter;
import com.juanlhiciano.app.models.service.IVoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    IVoterService voterService;

    @RequestMapping(value="test")
    public @ResponseBody List<Voter> getAll(){

        return voterService.findAll();

    }

    @RequestMapping(value="test/{leader_id}")
    public @ResponseBody List<Voter> getVotersByLeader(@PathVariable("leader_id") Long leader){

        return voterService.findByLeader(leader);

    }
}
