package com.ebsco.eis.medical.core.validators.dmp2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ebsco.eis.medical.core.validators.FieldValidator;

@Component
public class Dmp2FieldValidator extends FieldValidator {
	    
    private static final Set<String> KNOWN_FIELDS = new HashSet<>(
            Arrays.asList("title", "titleConcepts", "titleSurfaceForms", "type"));

    @Override
	protected Set<String> getKnownFields() {
		return KNOWN_FIELDS;
	}  
}