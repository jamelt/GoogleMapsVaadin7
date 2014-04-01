package com.vaadin.tapio.googlemaps.client.events;

import com.vaadin.tapio.googlemaps.client.LatLon;

/**
 * @author Jamel
 * @since 3/31/2014
 */
public interface RepositionListener {
	public void repositioned(LatLon position);
}
