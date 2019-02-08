package com.jcdecaux.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the developper database table.
 * 
 */
@Entity
@NamedQuery(name="Developper.findAll", query="SELECT d FROM Developper d")
public class Developper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nom;

	private String prenom;

	//bi-directional many-to-many association to Langage
	@ManyToMany
	@JoinTable(
		name="developper_langage"
		, joinColumns={
			@JoinColumn(name="developper_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="langage_id")
			}
		)
	@JsonIgnoreProperties("developpers")
	private List<Langage> langages;

	public Developper() {
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

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Langage> getLangages() {
		return this.langages;
	}

	public void setLangages(List<Langage> langages) {
		this.langages = langages;
	}

}