package com.bvr.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pathology")
public class PathologyService {

	public PathologyService() {
		// TODO Auto-generated constructor stub
	}

	List<Disease> diseases = Arrays.asList(
			new Disease("d1", "Dengue", "Be Safe from still water"),
			new Disease("d2", "HeadAche","hot water vapour"),
			new Disease("d3", "Corona", "Wash hands and stay safe"));
	
	@RequestMapping("/diseases")
	public DiseaseList diseases() {
		DiseaseList diseaseList = new DiseaseList();
		
		diseaseList.setDiseases(diseases);
		return diseaseList;
		
	}
	
	@RequestMapping("/diseases/{id}")
	public Disease getDiseaseById(@PathVariable("id") String id) {
		Disease d = diseases.stream()
				.filter(disease -> id.equals(disease.getId()))
				.findAny().orElse(null);
		return d;
	}
	
	
	
	
	
	
	
}
