package com.movie.fullstack.service;

import com.movie.fullstack.model.DatabaseSequence;
import com.movie.fullstack.model.Documentary;
import com.movie.fullstack.repository.DocumentaryRepository;
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
public class DocumentaryService {

    @Autowired
    private DocumentaryRepository documentaryRepository;

    public Documentary addDoc(Documentary documentary){
        documentary.setDoc_id(generateSequence(Documentary.SEQUENCE_NAME));
        return documentaryRepository.save(documentary);
    }

    public List<Documentary> getAllDocs(){
        return documentaryRepository.findAll();
    }

    public Documentary getDocById(String doc_id){
        Optional<Documentary> documentary = documentaryRepository.findById(doc_id);
        if(documentary.isPresent()){
            return documentary.get();
        }
        else{
            throw new RuntimeException();
        }
    }

    @Autowired
    private MongoOperations mongoOperations;

    public String generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return "D" + (!Objects.isNull(counter) ? counter.getSeq() : 1);
    }
}
