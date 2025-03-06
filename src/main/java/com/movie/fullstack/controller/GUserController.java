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

    /**
     * Endpoint to add a new user.
     * @param gUser The user object received from the request body.
     * @return ResponseEntity containing the saved user and HTTP status CREATED (201).
     */
    @PostMapping("/addGUser")
    public ResponseEntity<GUser> addGUser(@RequestBody GUser gUser){
        return new ResponseEntity<GUser>(guserService.addGUser(gUser), HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all users.
     * @return A list of all users.
     */
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


    /**
     * Endpoint to retrieve a user by their ID.
     * @param id The ID of the user.
     * @return The user object if found.
     */
    @PutMapping("/updateGUser/{id}")
    public ResponseEntity<?> updateGUser(@PathVariable String id, @RequestBody GUser gUser){

        GUser updateGUser = guserService.updateGUser(id,gUser);
        return ResponseEntity.ok(updateGUser);
    }

    /**
     * Endpoint to delete a user by ID.
     * @param id The ID of the user to be deleted.
     * @return A success message if the user is deleted.
     */
    @DeleteMapping("/deleteGUser/{id}")
    public String deleteGUser(@PathVariable String id){
        return guserService.deleteGUser(id);
    }


}

