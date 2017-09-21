package it.polito.tdp.metrodeparis.model;

public class Link {
	
	private Integer id;
	private Line line;
	private Station departure;
	private Station arrival;
	
	/**
	 * @param id
	 * @param line
	 * @param departure
	 * @param arrival
	 */
	public Link(Integer id, Line line, Station departure, Station arrival) {
		super();
		this.id = id;
		this.line = line;
		this.departure = departure;
		this.arrival = arrival;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(Line line) {
		this.line = line;
	}

	/**
	 * @return the departure
	 */
	public Station getDeparture() {
		return departure;
	}

	/**
	 * @param departure the departure to set
	 */
	public void setDeparture(Station departure) {
		this.departure = departure;
	}

	/**
	 * @return the arrival
	 */
	public Station getArrival() {
		return arrival;
	}

	/**
	 * @param arrival the arrival to set
	 */
	public void setArrival(Station arrival) {
		this.arrival = arrival;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return line + " -> " + departure + " - " + arrival;
	}

}
