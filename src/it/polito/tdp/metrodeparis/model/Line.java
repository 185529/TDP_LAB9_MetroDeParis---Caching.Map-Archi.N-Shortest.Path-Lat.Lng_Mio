package it.polito.tdp.metrodeparis.model;

public class Line {
	
	private Integer id;
	private String name;
	private Double speed;
	private Double gap;
	
	/**
	 * @param id
	 * @param name
	 * @param speed
	 * @param gap
	 */
	public Line(Integer id, String name, Double speed, Double gap) {
		super();
		this.id = id;
		this.name = name;
		this.speed = speed;
		this.gap = gap;
	}

	/**
	 * @param id
	 */
	public Line(Integer id) {
		super();
		this.id = id;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the speed
	 */
	public Double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	/**
	 * @return the gap
	 */
	public Double getGap() {
		return gap;
	}

	/**
	 * @param gap the gap to set
	 */
	public void setGap(Double gap) {
		this.gap = gap;
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
		Line other = (Line) obj;
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
		return "Line [name=" + name + "]";
	}

}
