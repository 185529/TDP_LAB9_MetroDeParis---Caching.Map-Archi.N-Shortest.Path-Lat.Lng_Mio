package it.polito.tdp.metrodeparis.model;

import java.util.List;

public class Path {
	
	private List<Station> elements;
	private double eta;
	
	/**
	 * @param elements
	 * @param eta
	 */
	public Path(List<Station> elements, Double eta) {
		super();
		this.elements = elements;
		this.eta = eta;
	}

	/**
	 * @return the elements
	 */
	public List<Station> getElements() {
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<Station> elements) {
		this.elements = elements;
	}

	/**
	 * @return the eta
	 */
	public Double getEta() {
		return eta;
	}

	/**
	 * @param eta the eta to set
	 */
	public void setEta(Double eta) {
		this.eta = eta;
	}
	
	public String getTextEta(){
		
		int hours = (int)eta / 3600;
		int minutes = ((int)eta % 3600) / 60;
		int seconds = (int)eta % 60;
		
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
		
	}

}
