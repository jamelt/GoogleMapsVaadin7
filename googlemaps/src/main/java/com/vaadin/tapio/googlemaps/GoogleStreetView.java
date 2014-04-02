package com.vaadin.tapio.googlemaps;

import com.vaadin.tapio.googlemaps.client.GoogleStreetViewState;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.NearestPositionListener;
import com.vaadin.tapio.googlemaps.client.model.NearestPositionDto;
import com.vaadin.tapio.googlemaps.client.rpcs.NearestPositionRpc;
import com.vaadin.tapio.googlemaps.client.rpcs.RepositionedRpc;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing Google Maps.
 */
public class GoogleStreetView extends com.vaadin.ui.AbstractComponent {

	private List<NearestPositionListener> nearestPositionListeners = new ArrayList<NearestPositionListener>();
	private final NearestPositionRpc nearestPositionRpc = new NearestPositionRpc() {
		@Override
		public void nearestPosition(NearestPositionDto nearestPosition) {
			for (NearestPositionListener listener : nearestPositionListeners)
				listener.nearestPosition(nearestPosition);
		}
	};
	private final RepositionedRpc repositionedRpc = new RepositionedRpc() {
		@Override
		public void repositioned(LatLon position) {
			getState().center = position;
		}
	};

	/**
	 * Initiates a new GoogleStreetView object with default settings from the
	 * {@link com.vaadin.tapio.googlemaps.client.GoogleStreetViewState state object}.
	 *
	 * @param apiKeyOrClientId The Maps API key from Google or the client ID for the
	 *                         Business API. All client IDs begin with a gme- prefix.
	 *                         Not required when developing in localhost.
	 */
	public GoogleStreetView(String apiKeyOrClientId) {
		if (isClientId(apiKeyOrClientId)) {
			getState().clientId = apiKeyOrClientId;
		} else {
			getState().apiKey = apiKeyOrClientId;
		}
		registerRpc(nearestPositionRpc);
		registerRpc(repositionedRpc);
	}

	/**
	 * Creates a new GoogleStreetView object with the given position. Other settings will
	 * be {@link com.vaadin.tapio.googlemaps.client.GoogleStreetViewState defaults of the state object}.
	 *
	 * @param position         Coordinates of the position.
	 * @param apiKeyOrClientId The Maps API key from Google or the client ID for the
	 *                         Business API. All client IDs begin with a gme- prefix.
	 *                         Not required when developing in localhost.
	 */
	public GoogleStreetView(LatLon position, String apiKeyOrClientId) {
		this(apiKeyOrClientId);
		getState().center = position;
	}

	/**
	 * Creates a new GoogleStreetView object with the given position and zoom. Other
	 * settings will be {@link com.vaadin.tapio.googlemaps.client.GoogleStreetViewState defaults of the state object}.
	 *
	 * @param position         Coordinates of the position.
	 * @param zoom             Amount of zoom.
	 * @param apiKeyOrClientId The Maps API key from Google or the client ID for the
	 *                         Business API. All client IDs begin with a gme- prefix.
	 *                         Not required when developing in localhost.
	 */
	public GoogleStreetView(LatLon position, double zoom, String apiKeyOrClientId) {
		this(apiKeyOrClientId);
		getState().center = position;
	}

	/**
	 * Creates a new GoogleStreetView object with the given position, zoom and language.
	 * Other settings will be {@link com.vaadin.tapio.googlemaps.client.GoogleStreetViewState defaults of the state
	 * object}.
	 *
	 * @param position         Coordinates of the position.
	 * @param zoom             Amount of zoom.
	 * @param apiKeyOrClientId The Maps API key from Google or the client ID for the
	 *                         Business API. All client IDs begin with a gme- prefix.
	 *                         Not required when developing in localhost.
	 * @param language         The language to use with maps. See
	 *                         https://developers.google.com/maps/faq#languagesupport for the
	 *                         list of the supported languages.
	 */
	public GoogleStreetView(LatLon position, double zoom, String apiKeyOrClientId, String language) {
		this(apiKeyOrClientId);
		getState().center = position;
		getState().language = language;
	}

	private static boolean isClientId(String apiKeyOrClientId) {
		return apiKeyOrClientId != null && apiKeyOrClientId.startsWith("gme-");
	}

	@Override
	protected GoogleStreetViewState getState() {
		return (GoogleStreetViewState) super.getState();
	}

	public void submitNearestLocationRequest(LatLon position) {
		getState().relocate = position;
	}

	/**
	 * Returns the current position of the street view.
	 *
	 * @return Coordinates of the center.
	 */
	public LatLon getPosition() {
		return getState().center;
	}

	/**
	 * Sets the position of the street view to the given coordinates.
	 *
	 * @param position The new coordinates of the center.
	 */
	public void setPosition(LatLon position) {
		getState().center = position;
	}

	public void addNearestPositionListener(NearestPositionListener nearestPositionListener) {
		nearestPositionListeners.add(nearestPositionListener);
	}
}
