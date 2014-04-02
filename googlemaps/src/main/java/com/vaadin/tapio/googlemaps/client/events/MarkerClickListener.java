package com.vaadin.tapio.googlemaps.client.events;

import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;

import java.io.Serializable;

/**
 * Interface for listening marker click events.
 * 
 */
public interface MarkerClickListener extends Serializable {
    /**
     * Handle a MarkerClickEvent.
     * 
     * @param clickedMarker
     *            The marker that was clicked.
     */
    public void markerClicked(GoogleMapMarker clickedMarker);
}
