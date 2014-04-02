package com.vaadin.tapio.googlemaps.client;

import com.google.gwt.ajaxloader.client.AjaxLoader;
import com.google.gwt.ajaxloader.client.AjaxLoader.AjaxLoaderOptions;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.StreetViewPanoramaData;
import com.google.maps.gwt.client.StreetViewService;
import com.google.maps.gwt.client.StreetViewStatus;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.client.ui.layout.ElementResizeEvent;
import com.vaadin.client.ui.layout.ElementResizeListener;
import com.vaadin.shared.ui.Connect;
import com.vaadin.tapio.googlemaps.GoogleStreetView;
import com.vaadin.tapio.googlemaps.client.events.NearestPositionListener;
import com.vaadin.tapio.googlemaps.client.events.RepositionListener;
import com.vaadin.tapio.googlemaps.client.model.NearestPositionDto;
import com.vaadin.tapio.googlemaps.client.rpcs.NearestPositionRpc;
import com.vaadin.tapio.googlemaps.client.rpcs.RepositionedRpc;

import static com.vaadin.tapio.googlemaps.client.DtoMapper.STREET_VIEW_PANORAMA_DATA_MAPPER;
import static com.vaadin.tapio.googlemaps.client.DtoMapper.STREET_VIEW_STATUS_MAPPER;

/**
 * The connector for the Google Maps JavaScript API v3.
 *
 * @author Tapio Aali <tapio@vaadin.com>
 * @author Jamel Toms <jameltoms@gmail.com>
 */
@Connect(GoogleStreetView.class)
public class GoogleStreetViewConnector extends AbstractComponentConnector
	implements RepositionListener, NearestPositionListener {

	private static final long serialVersionUID = 380172839234L;

	protected static boolean apiLoaded = false;
	protected static boolean mapInitiated = false;

	private RepositionedRpc repositionedRpc = RpcProxy.create(RepositionedRpc.class, this);
	private NearestPositionRpc nearestPositionRpc = RpcProxy.create(NearestPositionRpc.class, this);

	private boolean deferred = false;
	private StreetViewService.Callback streetViewServiceCallback = null;

	public GoogleStreetViewConnector() {
		streetViewServiceCallback = new StreetViewService.Callback() {
			@Override
			public void handle(StreetViewPanoramaData panoramaData, StreetViewStatus status) {
				NearestPositionDto dto = new NearestPositionDto();
				dto.setStreetViewPanoramaData(STREET_VIEW_PANORAMA_DATA_MAPPER.map(panoramaData));
				dto.setStreetViewStatus(STREET_VIEW_STATUS_MAPPER.map(status));
				nearestPosition(dto);
			}
		};
	}

	private void initMap() {
		getWidget().setVisualRefreshEnabled(getState().visualRefreshEnabled);
		getWidget().initStreetView(getState().center);
		getWidget().setRepositionListener(this);

		if (deferred) {
			loadDeferred();
			deferred = false;
		}

		getLayoutManager().addElementResizeListener(getWidget().getElement(),
			new ElementResizeListener() {
				@Override
				public void onElementResize(ElementResizeEvent e) {
					getWidget().triggerResize();
				}
			}
		);
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(GoogleStreetViewWidget.class);
	}

	@Override
	public GoogleStreetViewWidget getWidget() {
		return (GoogleStreetViewWidget) super.getWidget();
	}

	@Override
	public GoogleStreetViewState getState() {
		return (GoogleStreetViewState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// load API/init map
		if (!apiLoaded) {
			deferred = true;
			loadMapApi();
			apiLoaded = true;
			return;
		} else if (!getWidget().isMapInitiated()) {
			deferred = true;
			initMap();
			return;
		}

		// setting that require initiated map
		boolean initial = stateChangeEvent.isInitialStateChange();
		// do not set zoom/center again if the change originated from client
		if (!getState().locationFromClient || initial) {
			if (isLocationDifferent(getState().center, getWidget())) {
				getWidget().setPosition(getState().center);
			}
		}

		if (stateChangeEvent.hasPropertyChanged("visualRefreshEnabled") || initial) {
			getWidget().setVisualRefreshEnabled(getState().visualRefreshEnabled);
		}

		if (stateChangeEvent.hasPropertyChanged("relocate")) {
			LatLng loc = LatLng.create(getState().relocate.getLat(), getState().relocate.getLon());
			getWidget().getStreetViewService().getPanoramaByLocation(loc, getState().radiusMeters,
				streetViewServiceCallback);
		}

		if (initial) {
			getWidget().triggerResize();
		}

	}

	private boolean isLocationDifferent(LatLon center, GoogleStreetViewWidget widget) {
		return getState().center.getLat() != getWidget().getLatitude() ||
			getState().center.getLon() != getWidget().getLongitude();
	}

	private void loadMapApi() {
		AjaxLoaderOptions options = AjaxLoaderOptions.newInstance();

		StringBuilder otherParams = new StringBuilder("sensor=false");

		if (getState().language != null) {
			otherParams.append("&language=").append(getState().language);
		}
		options.setOtherParms(otherParams.toString());
		Runnable callback = new Runnable() {
			public void run() {
				initMap();
			}
		};

		AjaxLoader.init(getState().apiKey);
		AjaxLoader.loadApi("maps", "3", callback, options);
	}

	private void loadDeferred() {
	}

	@Override
	public void nearestPosition(NearestPositionDto nearestPosition) {
		nearestPositionRpc.nearestPosition(nearestPosition);
	}

	@Override
	public void repositioned(LatLon position) {
		repositionedRpc.repositioned(position);
	}
}
