package com.mongodb.mongodbtest.Service;

import com.mongodb.mongodbtest.Model.Composition;
import com.mongodb.mongodbtest.Repository.CompositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositionService {
    private final CompositionRepository compositionRepository;

    public CompositionService(CompositionRepository compositionRepository){
        this.compositionRepository = compositionRepository;
    }

    public List<Composition> getCompositions() { return compositionRepository.findAll(); }
    public Composition save(Composition composition) { return compositionRepository.save(composition); }
}
