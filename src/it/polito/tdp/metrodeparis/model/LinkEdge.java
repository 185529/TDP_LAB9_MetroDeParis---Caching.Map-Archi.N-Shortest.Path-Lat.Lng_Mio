package it.polito.tdp.metrodeparis.model;

import org.jgrapht.graph.DefaultWeightedEdge;

@SuppressWarnings("serial")
public class LinkEdge extends DefaultWeightedEdge {
	
	private Line line;
	
	public LinkEdge(){
		super();
	}
	
	public LinkEdge(Line line){
		super();
		this.line = line;
	}
	
	public void setLine(Line line){
		this.line = line;
	}
	
	public Line getLine(){
		return this.line;
	}

}
