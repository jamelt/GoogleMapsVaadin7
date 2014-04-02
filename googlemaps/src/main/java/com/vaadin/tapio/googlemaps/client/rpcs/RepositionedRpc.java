package com.vaadin.tapio.googlemaps.client.rpcs;

import com.vaadin.shared.communication.ServerRpc;
import com.vaadin.tapio.googlemaps.client.LatLon;

/**
 * @author Jamel Toms
 * @since 3/31/2014
 */
public interface RepositionedRpc extends ServerRpc {
	public void repositioned(LatLon position);
}
