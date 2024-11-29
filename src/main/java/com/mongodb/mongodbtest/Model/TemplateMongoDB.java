package com.mongodb.mongodbtest.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "templates")
@Data
public class TemplateMongoDB {
    @Id
    private String id;
    private String name;
    private String version;
    private Map<String, Object> metadata;
    private Map<String, Object> definition;

    public TemplateMongoDB(String templateId, String name, String version, Map<String, Object> definition, Map<String, Object> metadata) {
        this.id = templateId;
        this.name = name;
        this.version = version;
        this.metadata = metadata;
        this.definition = definition;
    }
}
