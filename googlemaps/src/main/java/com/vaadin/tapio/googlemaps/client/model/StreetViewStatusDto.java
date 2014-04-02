package com.vaadin.tapio.googlemaps.client.model;

import java.io.Serializable;

/**
 * @author Jamel
 * @since 4/2/2014
 */
public enum StreetViewStatusDto implements Serializable {
	OK, UNKNOWN_ERROR, ZERO_RESULTS;

	public static StreetViewStatusDto getByString(String value) {
		if (value.toUpperCase().contains("OK")) {
			return OK;
		} else if (value.toUpperCase().contains("UNKNOWN_ERROR")) {
			return UNKNOWN_ERROR;
		} else if (value.toUpperCase().contains("ZERO_RESULTS")) {
			return ZERO_RESULTS;
		}
		return null;
	}
}
