package com.jcdecaux.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcdecaux.model.Developper;
import com.jcdecaux.model.Langage;
import com.jcdecaux.repository.DevelopperRepository;
import com.jcdecaux.repository.LangageRepository;


@RestController
@RequestMapping("/api")
public class DevelopperController {
	
	@Autowired
	private DevelopperRepository developperRepository;
	
	@Autowired
	private LangageRepository langageRepository;

	@GetMapping("/developpers")
	public Iterable<Developper> findAll() {
		return developperRepository.findAll();
	}
	
	@GetMapping("/developpers/{langageId}")
	public Iterable<Developper> findAllByLangeId(@PathVariable(value = "langageId") int langageId) throws Exception {

		Langage langage = langageRepository.findById(langageId)
	            .orElseThrow(() -> new Exception("Developper not found!"));
		return langage.getDeveloppers();
	}
	
	@PostMapping("/developpers")
	public Developper createDevelopper(@Valid @RequestBody Developper developper) {
	    return developperRepository.save(developper);
	}
	
	@PutMapping("/developpers")
	public Developper updateNote(@Valid @RequestBody Developper newDevelopper) throws Exception {

		Developper developper = developperRepository.findById(newDevelopper.getId())
	            .orElseThrow(() -> new Exception("Developper not found!"));

	    developper.setNom(newDevelopper.getNom());
	    developper.setPrenom(newDevelopper.getPrenom());

	    Developper updatedDevelopper = developperRepository.save(developper);
	    return updatedDevelopper;
	}
	
	@PutMapping("/addLangageToDevelopper/{developperId}/{langageId}")
	public Developper updateNote(@PathVariable(value = "langageId") int langageId, 
			@PathVariable(value = "developperId") int developperId) throws Exception {

		Developper developper = developperRepository.findById(developperId)
	            .orElseThrow(() -> new Exception("Developper not found!"));
		
		Langage langage = langageRepository.findById(langageId)
	            .orElseThrow(() -> new Exception("Developper not found!"));

	    developper.getLangages().add(langage);
	    langage.getDeveloppers().add(developper);

	    Developper updatedDevelopper = developperRepository.save(developper);
	    Langage updatedLange = langageRepository.save(langage);
	    return updatedDevelopper;
	}

}
