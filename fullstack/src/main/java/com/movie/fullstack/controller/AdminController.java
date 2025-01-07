package com.movie.fullstack.controller;



import com.movie.fullstack.model.Admin;
import com.movie.fullstack.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
        return new ResponseEntity<Admin>(adminService.addAdmin(admin), HttpStatus.CREATED);
    }

    @GetMapping("/getAdminById/{id}")
    public Admin findById(@PathVariable String id){
        return adminService.getAdminById(id);
    }

    @GetMapping("/getAllAdmin")
    public List<Admin> getAllMovies(){
        return adminService.getAllAdmin();
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable String id, @RequestBody Admin admin){
        Admin updateAdmin = adminService.updateAdmin(id,admin);
        return ResponseEntity.ok(updateAdmin);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable String id) {
        return adminService.deleteAdmin(id);
    }
}
