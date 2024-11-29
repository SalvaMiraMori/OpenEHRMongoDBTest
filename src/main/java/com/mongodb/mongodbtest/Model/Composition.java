package com.mongodb.mongodbtest.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "compositions")
@Data
public class Composition {
    private RelatedTo relatedTo;
    private Map<String, Object> aqlSearchParams;
    private String canonicalJSON;

    public record RelatedTo(String ehrId, String compositionId, String templateId, List<String> archetypes) {}

    public String getCanonicalJSON(){ return canonicalJSON; }
}
