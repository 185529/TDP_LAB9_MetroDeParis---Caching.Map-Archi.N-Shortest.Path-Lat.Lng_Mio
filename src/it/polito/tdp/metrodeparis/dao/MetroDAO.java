package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.metrodeparis.model.Line;
import it.polito.tdp.metrodeparis.model.Link;
import it.polito.tdp.metrodeparis.model.Station;

public class MetroDAO {

	public Map<Integer, Station> loadAllStations() {

		final String sql = "SELECT id_fermata AS id, nome AS name, coordx AS cx, coordy AS cy FROM fermata ORDER BY nome ASC";
		
		Map<Integer, Station> stations = new HashMap<Integer, Station>();

		try {
			
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Station s = new Station(rs.getInt("id"), rs.getString("name"), new LatLng(rs.getDouble("cx"), rs.getDouble("cy")));
				stations.put(s.getId(), s);
			}

			rs.close();
			st.close();
			conn.close();
			
			return stations;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR: Database error - MetroDAO - loadAllStations()");
		}

	}
	
	public Map<Integer, Line> loadAllLines() {

		final String sql = "SELECT id_linea AS id, nome AS name, velocita AS speed, intervallo AS gap FROM linea ORDER BY nome ASC";
		
		Map<Integer, Line> lines = new HashMap<Integer, Line>();

		try {
			
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Line l = new Line(rs.getInt("id"), rs.getString("name"), rs.getDouble("speed"), rs.getDouble("gap"));
				lines.put(l.getId(), l);
			}

			rs.close();
			st.close();
			conn.close();
			
			return lines;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR: Database error - MetroDAO - loadAllLines()");
		}

	}
	
	public List<Link> loadAllLinks(Map<Integer, Station> stations, Map<Integer, Line> lines){

		final String sql = "SELECT id_connessione AS idC, id_linea AS idL, id_stazP AS idD, id_stazA AS idA FROM connessione";
		
		List<Link> links = new ArrayList<Link>();
		
		try {
			
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			// caching stations and lines!!
			
			while (rs.next()) {
				Link k = new Link(rs.getInt("idC"), lines.get(rs.getInt("idL")), stations.get(rs.getInt("idD")), stations.get(rs.getInt("idA")));
				links.add(k);
			}

			rs.close();
			st.close();
			conn.close();
			
			return links;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR: Database error - MetroDAO - loadAllLines()");
		}
		
	}
	
}
