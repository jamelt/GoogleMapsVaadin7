package com.vaadin.tapio.googlemaps.client;

/**
 * State for the Google Street View
 *
 * @author Jamel Toms <jameltoms@gmail.com>
 */
public class GoogleStreetViewState extends AbstractGoogleMapState {
	private static final long serialVersionUID = 837096123334L;

	public LatLon relocate = null;
	public double radiusMeters = 100.0;

	public GoogleStreetViewState() {
	}

}
