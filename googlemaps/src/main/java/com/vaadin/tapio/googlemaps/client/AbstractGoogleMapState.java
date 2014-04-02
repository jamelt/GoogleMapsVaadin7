package com.vaadin.tapio.googlemaps.client;

import com.vaadin.shared.AbstractComponentState;

/**
 * The shared state of the Google Maps. Contains also the default
 *
 * @author Tapio Aali <tapio@vaadin.com>
 */
public abstract class AbstractGoogleMapState extends AbstractComponentState {
	private static final long serialVersionUID = 818703416389L;

	public String apiKey = null;
	public String clientId = null;

	public String language = null;  // defaults to the language setting of the browser
	public LatLon center = new LatLon(51.477811, -0.001475);

	public boolean visualRefreshEnabled = false;
	public boolean locationFromClient = false;

	public boolean isBusiness() {
		return clientId != null;
	}

	public static boolean isClientId(String apiKeyOrClientId) {
		return apiKeyOrClientId != null && apiKeyOrClientId.startsWith("gme-");
	}
}
