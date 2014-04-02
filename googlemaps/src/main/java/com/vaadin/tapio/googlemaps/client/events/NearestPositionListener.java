package com.vaadin.tapio.googlemaps.client.events;

import com.vaadin.tapio.googlemaps.client.model.NearestPositionDto;

import java.io.Serializable;

/**
 * @author Jamel
 * @since 4/2/2014
 */
public interface NearestPositionListener extends Serializable {
	void nearestPosition(NearestPositionDto nearestPosition);
}
