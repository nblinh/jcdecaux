package com.jcdecaux.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the langage database table.
 * 
 */
@Entity
@NamedQuery(name="Langage.findAll", query="SELECT l FROM Langage l")
public class Langage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nom;

	//bi-directional many-to-many association to Developper
	@ManyToMany(mappedBy="langages")
	@JsonIgnoreProperties("langages")
	private List<Developper> developpers;

	public Langage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Developper> getDeveloppers() {
		return this.developpers;
	}

	public void setDeveloppers(List<Developper> developpers) {
		this.developpers = developpers;
	}

}