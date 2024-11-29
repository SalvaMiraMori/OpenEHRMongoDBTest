package com.mongodb.mongodbtest;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

@Component
public class OpenEHRPathExtractor {

    private final ObjectMapper objectMapper;
    private List<String> paths;

    public OpenEHRPathExtractor() {
        this.objectMapper = new ObjectMapper();
        this.paths = new ArrayList<>();
    }

    public List<String> extractPaths(String canonicalJson) {
        try {
            paths.clear();
            JsonNode rootNode = objectMapper.readTree(canonicalJson);
            processNode(rootNode, "");
            return paths;
        } catch (Exception e) {
            throw new RuntimeException("Error processing OpenEHR composition", e);
        }
    }

    private void processNode(JsonNode node, String currentPath) {
        if (node.isObject()) {
            // Processa tots els camps de l'objecte
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String fieldName = field.getKey();
                JsonNode fieldValue = field.getValue();

                String newPath = currentPath.isEmpty() ? fieldName : currentPath + "/" + fieldName;

                // Afegeix el path si conté dades
                if (isLeafNode(fieldValue) || isArchetypeNode(fieldName)) {
                    paths.add(newPath);
                }

                processNode(fieldValue, newPath);
            }
        } else if (node.isArray()) {
            // Processa els elements de l'array
            for (int i = 0; i < node.size(); i++) {
                processNode(node.get(i), currentPath + "[" + i + "]");
            }
        }
    }

    private boolean isLeafNode(JsonNode node) {
        return node.isValueNode() || node.isNull();
    }

    private boolean isArchetypeNode(String fieldName) {
        return fieldName.startsWith("archetype_node_id") ||
                fieldName.startsWith("archetype_details") ||
                fieldName.startsWith("name");
    }
}