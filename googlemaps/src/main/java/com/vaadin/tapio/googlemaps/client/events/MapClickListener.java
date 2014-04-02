package com.vaadin.tapio.googlemaps.client.events;

import com.vaadin.tapio.googlemaps.client.LatLon;

import java.io.Serializable;

/**
 * Interface for listening map click events.
 * 
 */
public interface MapClickListener extends Serializable {
    /**
     * Handle a MapClickListener.
     * 
     * @param position
     *            The position that was clicked.
     */
    public void mapClicked(LatLon position);
}
