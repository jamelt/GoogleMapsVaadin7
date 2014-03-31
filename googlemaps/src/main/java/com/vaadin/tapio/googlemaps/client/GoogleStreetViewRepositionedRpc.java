package com.vaadin.tapio.googlemaps.client;

import com.vaadin.shared.communication.ServerRpc;

/**
 * @author Jamel
 * @since 3/31/2014
 */
public interface GoogleStreetViewRepositionedRpc extends ServerRpc {
	public void repositioned(LatLon position);
}
