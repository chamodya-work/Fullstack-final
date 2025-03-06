package com.movie.fullstack.service;

import com.movie.fullstack.model.DatabaseSequence;
import com.movie.fullstack.model.GUser;
import com.movie.fullstack.repository.GUserRepository;
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
public class GUserService {

    @Autowired
    private GUserRepository gUserRepository;



    public GUser getUserById(String id){
        return gUserRepository.findById(id).orElse(null);
    }

    public GUser addGUser(GUser gUser){
        gUser.setGUser_id(generateSequence(GUser.SEQUENCE_NAME));
        return gUserRepository.save(gUser);
    }

    public List<GUser> getGUser() {
        return gUserRepository.findAll();
    }

    public GUser getGUserByEmail(String email){
        return gUserRepository.findByEmail(email);
    }

    public GUser updateGUser(String id, GUser guser) {
        Optional<GUser> optionalGuser = gUserRepository.findById(id);
        GUser existingGuser = null;
        if (optionalGuser.isPresent()) {
            existingGuser = optionalGuser.get();
            existingGuser.setName(guser.getName());
            existingGuser.setEmail(guser.getEmail());
            existingGuser.setPassword(guser.getPassword());
        }
        return gUserRepository.save(existingGuser);
    }

    public String deleteGUser(String id){
        gUserRepository.deleteById(id);
        return "User Deleted - id=" +id;
    }



    @Autowired
    private MongoOperations mongoOperations;

    public String generateSequence(String seqName) {
        DatabaseSequence counter= mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                DatabaseSequence.class);

        return "U" + (!Objects.isNull(counter)? counter.getSeq():1);
    }



}
