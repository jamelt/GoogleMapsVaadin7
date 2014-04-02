package com.vaadin.tapio.googlemaps.client.events;

import com.vaadin.tapio.googlemaps.client.LatLon;

import java.io.Serializable;

/**
 * @author Jamel
 * @since 3/31/2014
 */
public interface RepositionListener extends Serializable {
	public void repositioned(LatLon position);
}
