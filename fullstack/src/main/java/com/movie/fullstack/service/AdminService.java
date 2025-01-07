package com.movie.fullstack.service;

import com.movie.fullstack.model.Admin;
import com.movie.fullstack.model.DatabaseSequence;
import com.movie.fullstack.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin getAdminById(String id){
        return adminRepository.findById(id).orElse(null);

    }

    public Admin addAdmin(Admin admin){
        admin.setAdmin_id(generateSequence(Admin.SEQUENCE_NAME));
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public Admin updateAdmin(String id, Admin admin) {
        Optional<Admin> optionalGUser = adminRepository.findById(id);
        Admin existingGUser = null;
        if (optionalGUser.isPresent()) {
            existingGUser = optionalGUser.get();
            existingGUser.setName(admin.getName());
            existingGUser.setEmail(admin.getEmail());
            existingGUser.setPassword(admin.getPassword());


        }
        return adminRepository.save(existingGUser);
    }

    public String deleteAdmin(String id){
        adminRepository.deleteById(id);
        return "Admin Deleted - id=" +id;
    }


    @Autowired
    private MongoOperations mongoOperations;

    public String generateSequence(String seqName){
        DatabaseSequence counter =mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return "A" + (!Objects.isNull(counter) ? counter.getSeq() : 1);
    }

}

