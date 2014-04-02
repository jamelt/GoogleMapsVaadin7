package com.vaadin.tapio.googlemaps.client.rpcs;

import com.vaadin.shared.communication.ServerRpc;
import com.vaadin.tapio.googlemaps.client.model.NearestPositionDto;

/**
 * @author Jamel Toms
 * @since 4/2/2014
 */
public interface NearestPositionRpc extends ServerRpc {
	void nearestPosition(NearestPositionDto nearestPosition);
}
