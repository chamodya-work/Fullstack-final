package com.movie.fullstack.controller;

import com.movie.fullstack.model.GUser;
import com.movie.fullstack.service.GUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GUserController {

    @Autowired
    private GUserService guserService;

    @PostMapping("/addGUser")
    public ResponseEntity<GUser> addGUser(@RequestBody GUser gUser){
        return new ResponseEntity<GUser>(guserService.addGUser(gUser), HttpStatus.CREATED);
    }

    @GetMapping("/getAllGUser")
    public List<GUser> getGUser(){
        return guserService.getGUser();
    }

    @GetMapping("/getGUserById/{id}")
    public GUser findById(@PathVariable String id){
        return guserService.getUserById(id);
    }

    @GetMapping("/getGUserByEmail/{email}")
    public GUser findByEmail(@PathVariable String email){
        return guserService.getGUserByEmail(email);
    }

    @PutMapping("/updateGUser/{id}")
    public ResponseEntity<?> updateGUser(@PathVariable String id, @RequestBody GUser gUser){

        GUser updateGUser = guserService.updateGUser(id,gUser);
        return ResponseEntity.ok(updateGUser);
    }

    @DeleteMapping("/deleteGUser/{id}")
    public String deleteGUser(@PathVariable String id){
        return guserService.deleteGUser(id);
    }


}

