package com.ebsco.eis.dmp2.elasticsearch;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a class helper to work with an array of objects in an Elasticsearch.
 * We define these fields as 'nested' so we can query each object independently of the other objects in the array.
 * Please see the elasticsearch document for more details:
 *
 * <p>https://www.elastic.co/guide/en/elasticsearch/reference/current/array.html
 *
 * <p>TODO: At some point we can simply query the ES mapping to tell us which fields are nested and remove this code.
 */
public class NestedFields {
    private static final String SECTIONS_PATH = "sections";
    private static final Map<String, String> NESTED_FIELDS = new HashMap<>();

    private NestedFields() {
        // static class
    }
    
    /**
     * Key represents the 'nested' field
     * Value represent the 'path' to the nested field
     */
    static {
        NESTED_FIELDS.put("sections.breadcrumb", SECTIONS_PATH);
        NESTED_FIELDS.put("sections.content", SECTIONS_PATH);
        NESTED_FIELDS.put("sections.heading", SECTIONS_PATH);
        NESTED_FIELDS.put("sections.weight", SECTIONS_PATH);
    }

    public static boolean isNestedField(final String field) {
        return NESTED_FIELDS.containsKey(field);
    }

    public static String getNestedPath(final String field) {
        return NESTED_FIELDS.get(field);
    }
}
