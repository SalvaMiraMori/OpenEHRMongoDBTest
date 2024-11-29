package com.mongodb.mongodbtest;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;

@Component
public class OpenEHRPathAndValueExtractor {

    private final ObjectMapper objectMapper;
    private final Map<String, Object> pathValues;

    public OpenEHRPathAndValueExtractor() {
        this.objectMapper = new ObjectMapper();
        this.pathValues = new LinkedHashMap<>();
    }

    public Map<String, Object> extractPathsAndValues(String canonicalJson) {
        try {
            pathValues.clear();
            JsonNode rootNode = objectMapper.readTree(canonicalJson);
            processNode(rootNode, "");
            return pathValues;
        } catch (Exception e) {
            throw new RuntimeException("Error processing OpenEHR composition", e);
        }
    }

    private void processNode(JsonNode node, String currentPath) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String fieldName = field.getKey();
                JsonNode fieldValue = field.getValue();

                String newPath = currentPath.isEmpty() ? fieldName : currentPath + "/" + fieldName;

                if (isLeafNode(fieldValue)) {
                    pathValues.put(newPath, extractValue(fieldValue));
                } else if (isArchetypeNode(fieldName)) {
                    pathValues.put(newPath, fieldValue.toString());
                }

                processNode(fieldValue, newPath);
            }
        } else if (node.isArray()) {
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

    private Object extractValue(JsonNode node) {
        if (node.isNull()) {
            return null;
        } else if (node.isTextual()) {
            return node.asText();
        } else if (node.isInt()) {
            return node.asInt();
        } else if (node.isLong()) {
            return node.asLong();
        } else if (node.isDouble()) {
            return node.asDouble();
        } else if (node.isBoolean()) {
            return node.asBoolean();
        } else {
            return node.toString();
        }
    }
}