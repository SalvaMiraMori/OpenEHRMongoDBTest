package com.mongodb.mongodbtest.Service;

import com.mongodb.mongodbtest.Model.Template;
import com.mongodb.mongodbtest.Model.TemplateMongoDB;
import com.mongodb.mongodbtest.Repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {
    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository){
        this.templateRepository = templateRepository;
    }

    public List<TemplateMongoDB> getTemplates(){ return templateRepository.findAll(); }
    public Optional<TemplateMongoDB> getTemplateByName(String name){ return templateRepository.findById(name); }
    public TemplateMongoDB save(TemplateMongoDB template){ return templateRepository.save(template); }
}
