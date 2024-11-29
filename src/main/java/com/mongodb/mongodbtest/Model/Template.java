package com.mongodb.mongodbtest.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

public class Template {
    private String name;
    private String version;
    private Map<String, Object> metadata;
    private Map<String, Object> definition;

    public String createId(){
        return name + " " + version.substring(0, version.length() - 4);
    }

    public void adaptDataToMongoDB(){
        version = version.substring(1, version.length() - 4);
    }

    public String getName(){return name;}

    public String getVersion(){return version;}

    public Map<String, Object> getMetadata(){return metadata; }

    public Map<String, Object> getDefinition(){ return definition; }
}
