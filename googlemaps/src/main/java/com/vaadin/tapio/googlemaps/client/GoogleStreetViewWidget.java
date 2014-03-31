package com.vaadin.tapio.googlemaps.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.StreetViewPanorama;
import com.google.maps.gwt.client.StreetViewPanoramaOptions;
import com.vaadin.tapio.googlemaps.client.events.RepositionListener;

/**
 * Client side widget to display Google Street View. Based on GoogleMapWidget.
 *
 * @author Jamel Toms <jameltoms@gmail.com>
 */
public class GoogleStreetViewWidget extends FlowPanel implements RequiresResize {

    public static final String CLASSNAME = "googlestreetview";
    private StreetViewPanorama streetView;
    private StreetViewPanoramaOptions streetViewOptions;
		private RepositionListener repositionListener = null;
    private LatLng position = null;

    public GoogleStreetViewWidget() {
        setStyleName(CLASSNAME);
    }

    public void initStreetView(LatLon center) {
        this.position = LatLng.create(center.getLat(), center.getLon());
        streetViewOptions = StreetViewPanoramaOptions.create();
        streetViewOptions.setPosition(this.position);
        streetView = StreetViewPanorama.create(getElement(), streetViewOptions);

				streetView.addPositionChangedListener(new StreetViewPanorama.PositionChangedHandler() {
					@Override
					public void handle() {
						position = streetView.getPosition();
					}
				});
    }


    public boolean isMapInitiated() {
        return !(streetView == null);
    }

    public void setPosition(LatLon position) {
        this.position = LatLng.create(position.getLat(), position.getLon());
        streetViewOptions.setPosition(this.position);
        streetView.setPosition(this.position);
    }


    public double getLatitude() {
        return streetView.getPosition().lat();
    }

    public double getLongitude() {
        return streetView.getPosition().lng();
    }

    public StreetViewPanorama getStreetView() {
        return streetView;
    }

    public void triggerResize() {
        Timer timer = new Timer() {
            @Override
            public void run() {
                streetView.triggerResize();
                streetView.setPosition(position);
            }
        };
        timer.schedule(20);
    }

    public native void setVisualRefreshEnabled(boolean enabled)
        /*-{
            $wnd.google.maps.visualRefresh = enabled;
        }-*/;

    @Override
    public void onResize() {
        triggerResize();
    }

	public void setRepositionListener(RepositionListener repositionListener) {
		this.repositionListener = repositionListener;
	}
}
