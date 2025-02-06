package com.movie.fullstack.controller;



import com.movie.fullstack.model.Admin;
import com.movie.fullstack.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Admin-related operations.
 * Handles CRUD operations for Admin entities.
 */

@RestController
@CrossOrigin
public class AdminController {

    // Injecting the AdminService to handle business logic
    @Autowired
    private AdminService adminService;

     
    //Adds a new admin to the system.

    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
        return new ResponseEntity<Admin>(adminService.addAdmin(admin), HttpStatus.CREATED);
    }

    //Retrieves an Admin by its unique ID.
    @GetMapping("/getAdminById/{id}")
    public Admin findById(@PathVariable String id){
        return adminService.getAdminById(id);
    }

    //Retrieves a list of all Admin users in the system.
    @GetMapping("/getAllAdmin")
    public List<Admin> getAllMovies(){
        return adminService.getAllAdmin();
    }

    //Updates an existing Admin's details based on the provided ID.
    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable String id, @RequestBody Admin admin){
        Admin updateAdmin = adminService.updateAdmin(id,admin);
        return ResponseEntity.ok(updateAdmin);
    }

    //Deletes an Admin from the system based on the provided ID.
    @DeleteMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable String id) {
        return adminService.deleteAdmin(id);
    }
}
