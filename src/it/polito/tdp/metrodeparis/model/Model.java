package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.WeightedMultigraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	private MetroDAO dao;
	
	private Map<Integer, Station> stations;
	private Map<Integer, Line> lines;
	private List<Link> links;
	
	private WeightedMultigraph<Station, LinkEdge> graph;
	
	public Model(){
		this.dao = new MetroDAO();
	}

	public Collection<Station> getStationList(){
		
		return new ArrayList<Station>(loadAllStations().values());
		
	}
	
	private Map<Integer, Station> loadAllStations(){
		
		if(stations == null){
			stations = dao.loadAllStations();
		}
		
		return stations;
		
	}
	
	private Map<Integer, Line> loadAllLines(){
		
		if(lines == null){
			lines = dao.loadAllLines();
		}
		
		return lines;
		
	}
	
	private List<Link> loadAllLinks(Map<Integer, Station> stations, Map<Integer, Line> lines){
		
		if(links == null){
			links = dao.loadAllLinks(stations, lines);
		}
		
		return links;
		
	}

	public void createGraph() {
		
		stations = loadAllStations();
		lines = loadAllLines();
		links = loadAllLinks(stations, lines);
		
		graph = new WeightedMultigraph<Station, LinkEdge>(LinkEdge.class);
		
		Graphs.addAllVertices(graph, stations.values());
		
		System.out.println("Vertexes added succesfully.");
		
		for(Link l : links){
			
			Double speed = l.getLine().getSpeed();
			Double distance = LatLngTool.distance(l.getDeparture().getPosition(), l.getArrival().getPosition(), LengthUnit.KILOMETER);
			Double time = (distance / speed) * 3600;
			
			LinkEdge le = new LinkEdge(l.getLine());
			graph.addEdge(l.getDeparture(), l.getArrival(), le);
			graph.setEdgeWeight(le, time);
			
		}
		
	}

	public Path getShortestPath(Station departure, Station arrival) {
		
		DijkstraShortestPath<Station, LinkEdge> sp = new DijkstraShortestPath<Station, LinkEdge>(graph, departure, arrival);
		
		List<LinkEdge> edges = sp.getPathEdgeList();
		
		if(edges == null){
			return null;
		}
		
		Double eta = sp.getPathLength();
		
		List<Station> stations = new ArrayList<Station>();
		
		for(LinkEdge le : edges){
			stations.add(graph.getEdgeTarget(le));
		}
		
		if (edges.size() - 1 > 0) {
			eta += (edges.size() - 1) * 30;
		}
		
		Path p = new Path(stations, eta);
		
		return p;
		
	}

}
