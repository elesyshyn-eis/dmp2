package com.ebsco.eis.medical.core.validators.dh;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ebsco.eis.medical.core.validators.FieldValidator;

@Component
public class DhFieldValidator extends FieldValidator {
	    
    private static final Set<String> KNOWN_FIELDS = new HashSet<>(
            Arrays.asList("nursing-specialty", "min-age","max-age", "gender", 
                    "publication-date", "timestamp", "title", 
                    "toc", "videoIDs", "imageIDs", "loader-guid",
                    "content-directory-structure.path", "content-directory-structure.node",
                    "content-directory-structure.position", 
                    "skill-label", "shortDescription", 
                    "version", "customerId"));

	@Override
	protected Set<String> getKnownFields() {
		return KNOWN_FIELDS;
	}    
}