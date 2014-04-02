package com.vaadin.tapio.googlemaps.client.events;

import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;

import java.io.Serializable;

/**
 * Interface for listening info window close events initiated by the user.
 * 
 * @author Tapio Aali <tapio@vaadin.com>
 */
public interface InfoWindowClosedListener extends Serializable {

    /**
     * Handle an info window close event
     * 
     * @param window
     *            the info window that was closed.
     */
    public void infoWindowClosed(GoogleMapInfoWindow window);
}
