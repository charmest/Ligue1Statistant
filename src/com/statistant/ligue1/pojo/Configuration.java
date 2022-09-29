package com.statistant.ligue1.pojo;

public class Configuration {

	private int id;
	private Float coefficientConfrontationsDirectes;
	private Float coefficientClassement;
	private Float coefficientClassementDomExt;
	private Float coefficientForme;
	private Float coefficientFormeDomExt;
	private Float coefficientResultats;
	private Float coefficientResultatsDomExt;
	private Float coefficientResultatsImp;
	private Float coefficientGlobal;

	public Configuration(int id, Float coefficientConfrontationsDirectes, Float coefficientClassement,
			Float coefficientClassementDomExt, Float coefficientForme, Float coefficientFormeDomExt, Float coefficientResultats,
			Float coefficientResultatsDomExt, Float coefficientResultatsImp, Float coefficientGlobal) {
		setId(id);
		setCoefficientConfrontationsDirectes(coefficientConfrontationsDirectes);
		setCoefficientClassement(coefficientClassement);
		setCoefficientClassementDomExt(coefficientClassementDomExt);
		setCoefficientForme(coefficientForme);
		setCoefficientFormeDomExt(coefficientFormeDomExt);
		setCoefficientResultats(coefficientResultats);
		setCoefficientResultatsDomExt(coefficientResultatsDomExt);
		setCoefficientResultatsImp(coefficientResultatsImp);
		setCoefficientGlobal(coefficientGlobal);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getCoefficientConfrontationsDirectes() {
		return coefficientConfrontationsDirectes;
	}

	public void setCoefficientConfrontationsDirectes(Float coefficientConfrontationsDirectes) {
		this.coefficientConfrontationsDirectes = coefficientConfrontationsDirectes;
	}

	public Float getCoefficientClassement() {
		return coefficientClassement;
	}

	public void setCoefficientClassement(Float coefficientClassement) {
		this.coefficientClassement = coefficientClassement;
	}

	public Float getCoefficientClassementDomExt() {
		return coefficientClassementDomExt;
	}

	public void setCoefficientClassementDomExt(Float coefficientClassementDomExt) {
		this.coefficientClassementDomExt = coefficientClassementDomExt;
	}

	public Float getCoefficientForme() {
		return coefficientForme;
	}

	public void setCoefficientForme(Float coefficientForme) {
		this.coefficientForme = coefficientForme;
	}

	public Float getCoefficientFormeDomExt() {
		return coefficientFormeDomExt;
	}

	public void setCoefficientFormeDomExt(Float coefficientFormeDomExt) {
		this.coefficientFormeDomExt = coefficientFormeDomExt;
	}

	public Float getCoefficientResultats() {
		return coefficientResultats;
	}

	public void setCoefficientResultats(Float coefficientResultats) {
		this.coefficientResultats = coefficientResultats;
	}

	public Float getCoefficientResultatsDomExt() {
		return coefficientResultatsDomExt;
	}

	public void setCoefficientResultatsDomExt(Float coefficientResultatsDomExt) {
		this.coefficientResultatsDomExt = coefficientResultatsDomExt;
	}

	public Float getCoefficientResultatsImp() {
		return coefficientResultatsImp;
	}

	public void setCoefficientResultatsImp(Float coefficientResultatsImp) {
		this.coefficientResultatsImp = coefficientResultatsImp;
	}

	public Float getCoefficientGlobal() {
		return coefficientGlobal;
	}

	public void setCoefficientGlobal(Float coefficientGlobal) {
		this.coefficientGlobal = coefficientGlobal;
	}

}
