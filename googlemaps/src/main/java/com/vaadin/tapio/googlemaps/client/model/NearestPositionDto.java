package com.vaadin.tapio.googlemaps.client.model;

import java.io.Serializable;

/**
 * @author Jamel
 * @since 4/2/2014
 */
public class NearestPositionDto implements Serializable {

	private StreetViewPanoramaDataDto streetViewPanoramaData;
	private StreetViewStatusDto streetViewStatus;

	public StreetViewPanoramaDataDto getStreetViewPanoramaData() {
		return streetViewPanoramaData;
	}

	public void setStreetViewPanoramaData(StreetViewPanoramaDataDto streetViewPanoramaData) {
		this.streetViewPanoramaData = streetViewPanoramaData;
	}

	public StreetViewStatusDto getStreetViewStatus() {
		return streetViewStatus;
	}

	public void setStreetViewStatus(StreetViewStatusDto streetViewStatus) {
		this.streetViewStatus = streetViewStatus;
	}
}
