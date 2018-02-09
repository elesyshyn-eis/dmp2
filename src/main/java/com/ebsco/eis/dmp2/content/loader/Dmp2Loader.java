package com.ebsco.eis.dmp2.content.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import com.ebsco.eis.dmp2.content.pojo.Abody;
import com.ebsco.eis.dmp2.content.pojo.Child;
import com.ebsco.eis.dmp2.content.pojo.DocumentRoot;
import com.ebsco.eis.dmp2.elasticsearch.ElasticRestClient;
import com.ebsco.eis.dmp2.elasticsearch.pojo.Dmp2;
import com.ebsco.eis.dmp2.elasticsearch.pojo.Section;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Dmp2Loader {
	
    @Autowired
    private ElasticRestClient esClient;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(Dmp2Loader.class);
	
	public int loadData(String indexName, boolean rollup) throws ResourceNotFoundException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		int count = 0;
		
		// Get the sample documents from the resource folder
		InputStream documents = getResourceAsStream("documents.json");
		
		// Use the scanner class to read the file... 
		Scanner sc = new Scanner(documents);
		
		// For each line in the file..
		while(sc.hasNext()) {
			
			try {
				
				// Map the JSON to an abody
				Abody abody = mapper.readValue(sc.nextLine(), Abody.class);
				
				// Ignore DynaCalc & Images
				if (abody.getType().equalsIgnoreCase("DynaCalc") || abody.getType().equalsIgnoreCase("Image")) {
					continue;
				}
				
				// Create the DMP2 object that elastic search expects to receive
				Dmp2 document = new Dmp2();
				document.setFilename(abody.getFilename());
				document.setTitle(abody.getTitle());
				document.setType(abody.getType());
				
				// Create a map to hold the section content
				Map<Integer, Section> sections = new HashMap<Integer, Section>();
						
				// Create the document root section and insert it into the map
				DocumentRoot documentRoot = abody.getDocumentRoot();
				Section section = new Section("abody", "", documentRoot.getLocation());
				section.setWeight(1.0);
				sections.put(documentRoot.getNodeId(), section);
				
				// Process the children under the document root
				for(Child child : documentRoot.getChildren()) {
					processChild(sections, child, rollup);
				}
				
				// Append all the sections to the document
				for(Section compiledSection : sections.values()) {
					document.addSection(compiledSection);
				}
				
				// Insert the document into elasticsearch
				esClient.executeRequest("PUT", "/" + indexName + "/dmp2/" + document.getFilename(), mapper.writeValueAsString(document));
				count++;
				
				
			} catch (Exception ex) {
				LOGGER.error("Unable to load json into elastisearch");
			}
		}
		
		// Close the stream
		sc.close();
	
		return count;
		
	}
	
	
	private void processChild(Map<Integer, Section> sections, Child child, boolean rollup) {
			
		// If we want to rollup the subsection content... 
		if (rollup && !child.getIsTopicOrSection() && !child.getIsConclusion()) {
			
			// look up the parent so we can append the content
			if (sections.containsKey(child.getParent())) {
				Section parent = sections.get(child.getParent());
				parent.setContent(parent.getContent() + " " +  child.getContent());
			} 
		
		// Otherwise, create a new section
		} else {
			
			Section newSection = new Section(child.getAnchor(), child.getContent(), child.getLocation());
			newSection.setWeight(getSectionBoost(child));
			sections.put(child.getNodeId(), newSection);
		}
	
		
		// If we have nested children, then process them recursively 
		if(!child.getChildren().isEmpty()) {
			
			for(Child subsection : child.getChildren()) {
				processChild(sections, subsection, rollup);
			}	
		}
	}
	
	
	private Double getSectionBoost(Child child) {

		// If this is topic
		if (child.getIsTopicOrSection()) {

			String anchorLowerCase = child.getAnchor().toLowerCase();

			if (anchorLowerCase.contains("recommendations")) {
				return 1.07;

			} else if (anchorLowerCase.contains("guidelines")) {
				return 1.06;

			} else if (anchorLowerCase.equals("sec-related-summaries")) {
				return 0.7;

			} else if (anchorLowerCase.contains("references") || anchorLowerCase.contains("also")
					|| anchorLowerCase.contains("medline") || anchorLowerCase.contains("acknowledgement")) {
				return 0.0;
			}
		}

		// If this child contains a conclusion, then add a boost to this section
		else if (child.getIsConclusion()) {
			return 1.04;
		}

		return 1.0;
	}

	private InputStream getResourceAsStream(final String resourceName) throws IOException, ResourceNotFoundException {
        return ClassLoader.getSystemResourceAsStream(resourceName);
    }	
}
