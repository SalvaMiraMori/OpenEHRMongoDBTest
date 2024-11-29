package com.mongodb.mongodbtest.Controller;

import com.mongodb.mongodbtest.Model.Template;
import com.mongodb.mongodbtest.Model.TemplateMongoDB;
import com.mongodb.mongodbtest.Service.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService){
        this.templateService = templateService;
    }

    @GetMapping
    public List<TemplateMongoDB> getTemplates(){ return templateService.getTemplates(); }

    @PostMapping
    public ResponseEntity<TemplateMongoDB> create(@RequestBody Template template) {
        String templateId = template.createId();
        template.adaptDataToMongoDB();
        TemplateMongoDB templateMongoDB = new TemplateMongoDB(templateId, template.getName(), template.getVersion(), template.getDefinition(), template.getMetadata());
        return ResponseEntity.status(HttpStatus.CREATED).body(templateService.save(templateMongoDB));
    }

}
