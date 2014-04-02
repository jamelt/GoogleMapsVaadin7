package com.vaadin.tapio.googlemaps.client.model;

import com.vaadin.tapio.googlemaps.client.LatLon;

import java.io.Serializable;

/**
 * @author Jamel
 * @since 4/2/2014
 */
public class StreetViewLocationDto implements Serializable {
	private String description;
	private LatLon latLon;

	/**
	 * A unique identifier for the panorama. This is stable within a session
	 * but unstable across sessions.
	 */
	private String pano;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LatLon getLatLon() {
		return latLon;
	}

	public void setLatLon(LatLon latLon) {
		this.latLon = latLon;
	}

	public String getPano() {
		return pano;
	}

	public void setPano(String pano) {
		this.pano = pano;
	}
}
