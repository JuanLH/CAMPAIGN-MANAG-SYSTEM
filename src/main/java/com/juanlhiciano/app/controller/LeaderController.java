package com.juanlhiciano.app.controller;


import com.google.gson.Gson;
import com.juanlhiciano.app.models.entity.Leader;
import com.juanlhiciano.app.models.entity.Voter;
import com.juanlhiciano.app.models.service.ILeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/leader")
public class LeaderController {

    @Autowired
    ILeaderService leaderService;

    @RequestMapping(value="test")
    public @ResponseBody List<Leader> getAll(){
        return leaderService.findAll();

    }

}
