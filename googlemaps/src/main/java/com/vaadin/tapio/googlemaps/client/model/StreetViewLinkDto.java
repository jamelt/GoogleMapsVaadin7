package com.vaadin.tapio.googlemaps.client.model;

import java.io.Serializable;

/**
 * @author Jamel
 * @since 4/2/2014
 */
public class StreetViewLinkDto implements Serializable {
	private String description;
	private double heading;
	/**
	 * A unique identifier for the panorama. This id is stable within a
	 * session but unstable across sessions.
	 */
	private String pano;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}

	public String getPano() {
		return pano;
	}

	public void setPano(String pano) {
		this.pano = pano;
	}
}
