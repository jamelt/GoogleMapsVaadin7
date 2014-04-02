package com.vaadin.tapio.googlemaps.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jamel
 * @since 4/2/2014
 */
public class StreetViewPanoramaDataDto implements Serializable {

	private String copyright;
	private String imageDate;
	private List<StreetViewLinkDto> streetViewLinks;
	private StreetViewLocationDto streetViewLocation;
	private StreetViewTileDataDto streetViewTileData;

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getImageDate() {
		return imageDate;
	}

	public void setImageDate(String imageDate) {
		this.imageDate = imageDate;
	}

	public List<StreetViewLinkDto> getStreetViewLinks() {
		return streetViewLinks;
	}

	public void setStreetViewLinks(List<StreetViewLinkDto> streetViewLinks) {
		this.streetViewLinks = streetViewLinks;
	}

	public StreetViewLocationDto getStreetViewLocation() {
		return streetViewLocation;
	}

	public void setStreetViewLocation(StreetViewLocationDto streetViewLocation) {
		this.streetViewLocation = streetViewLocation;
	}

	public StreetViewTileDataDto getStreetViewTileData() {
		return streetViewTileData;
	}

	public void setStreetViewTileData(StreetViewTileDataDto streetViewTileData) {
		this.streetViewTileData = streetViewTileData;
	}
}
