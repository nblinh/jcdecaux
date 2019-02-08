package com.jcdecaux.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcdecaux.model.Langage;
import com.jcdecaux.repository.LangageRepository;


@RestController
@RequestMapping("/api")
public class LangageController {
	
	@Autowired
	private LangageRepository langageRepository;

	@GetMapping("/langages")
	public Iterable<Langage> findAll() {
		return langageRepository.findAll();
	}
	
	@PostMapping("/langages")
	public Langage createLangage(@Valid @RequestBody Langage langage) {
	    return langageRepository.save(langage);
	}
	
	

}
