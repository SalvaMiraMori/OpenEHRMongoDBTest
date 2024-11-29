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
    // @Id
    // private String _id;
    // private String compositionType;
    // private String ehrId;
    // private String timestamp;
    private RelatedTo relatedTo;
    private Map<String, Object> aqlSearchParams;
    // private List<Relation> RelatedTo;
    // private List<EnrichedData> EnrichedData;
    private String canonicalJSON;

    // public record AQLSearchParams(Map<String, Object> AQLSearchParams) {}
    // public record Relation(String entity, String id) {}
    // public record EnrichedData(List<String> tags, List<Double> similarityVector) {}
    public record RelatedTo(String ehrId, String compositionId, String templateId, List<String> archetypes) {}

    public String getCanonicalJSON(){ return canonicalJSON; }
}
