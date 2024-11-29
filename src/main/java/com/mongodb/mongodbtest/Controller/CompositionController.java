package com.mongodb.mongodbtest.Controller;

import com.mongodb.mongodbtest.Model.Composition;
import com.mongodb.mongodbtest.OpenEHRPathAndValueExtractor;
import com.mongodb.mongodbtest.OpenEHRPathExtractor;
import com.mongodb.mongodbtest.Repository.CompositionRepository;
import com.mongodb.mongodbtest.Service.CompositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compositions")
public class CompositionController {

    private final CompositionService compositionService;

    public CompositionController(CompositionService compositionService, CompositionRepository compositionRepository){
        this.compositionService = compositionService;
    }

    @GetMapping
    public List<Composition> getCompositions(){ return compositionService.getCompositions(); }

    @PostMapping
    public ResponseEntity<Composition> create(@RequestBody Composition composition) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compositionService.save(composition));
    }

    @RequestMapping("/pathsAndValues")
    @GetMapping
    public Map<String, Object> getCompositionsPathsAndValues(@RequestBody Composition composition){
        OpenEHRPathAndValueExtractor openEHRPathAndValueExtractor = new OpenEHRPathAndValueExtractor();
        return openEHRPathAndValueExtractor.extractPathsAndValues(composition.getCanonicalJSON());
    }

    @RequestMapping("/paths")
    @GetMapping
    public List<String> getCompositionsPath(@RequestBody Composition composition){
        OpenEHRPathExtractor openEHRPathExtractor = new OpenEHRPathExtractor();
        return openEHRPathExtractor.extractPaths(composition.getCanonicalJSON());
    }
}
