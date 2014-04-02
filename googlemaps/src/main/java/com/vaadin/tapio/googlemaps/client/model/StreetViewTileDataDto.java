package com.vaadin.tapio.googlemaps.client.model;

import java.io.Serializable;

/**
 * @author Jamel
 * @since 4/2/2014
 */
public class StreetViewTileDataDto implements Serializable {
	/**
	 * The heading (in degrees) at the center of the panoramic tiles.
	 */
	private double centerHeading;
	private SizeDto tileSize;
	private String tileUrl;
	private SizeDto worldSize;

	public double getCenterHeading() {
		return centerHeading;
	}

	public void setCenterHeading(double centerHeading) {
		this.centerHeading = centerHeading;
	}

	public SizeDto getTileSize() {
		return tileSize;
	}

	public void setTileSize(SizeDto tileSize) {
		this.tileSize = tileSize;
	}

	public String getTileUrl() {
		return tileUrl;
	}

	public void setTileUrl(String tileUrl) {
		this.tileUrl = tileUrl;
	}

	public SizeDto getWorldSize() {
		return worldSize;
	}

	public void setWorldSize(SizeDto worldSize) {
		this.worldSize = worldSize;
	}
}
