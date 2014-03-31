package com.vaadin.tapio.googlemaps.client;

import com.google.gwt.ajaxloader.client.AjaxLoader;
import com.google.gwt.ajaxloader.client.AjaxLoader.AjaxLoaderOptions;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.client.ui.layout.ElementResizeEvent;
import com.vaadin.client.ui.layout.ElementResizeListener;
import com.vaadin.shared.ui.Connect;
import com.vaadin.tapio.googlemaps.GoogleStreetView;
import com.vaadin.tapio.googlemaps.client.events.RepositionListener;

/**
 * The connector for the Google Maps JavaScript API v3.
 *
 * @author Tapio Aali <tapio@vaadin.com>
 * @author Jamel Toms <jameltoms@gmail.com>
 */
@Connect(GoogleStreetView.class)
public class GoogleStreetViewConnector extends AbstractComponentConnector implements
	RepositionListener {

    private static final long serialVersionUID = 380172839234L;

    protected static boolean apiLoaded = false;
    protected static boolean mapInitiated = false;

	private GoogleStreetViewRepositionedRpc repositionedRpc = RpcProxy.create(
		GoogleStreetViewRepositionedRpc.class, this);


	private boolean deferred = false;

    public GoogleStreetViewConnector() {
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
                });
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
            if (getState().center.getLat() != getWidget().getLatitude() ||
                    getState().center.getLon() != getWidget().getLongitude()) {
                getWidget().setPosition(getState().center);
            }
        }

        if (stateChangeEvent.hasPropertyChanged("visualRefreshEnabled") || initial) {
            getWidget().setVisualRefreshEnabled(getState().visualRefreshEnabled);
        }

        if (initial) {
            getWidget().triggerResize();
        }

    }

    private void loadMapApi() {
        AjaxLoaderOptions options = AjaxLoaderOptions.newInstance();

        StringBuffer otherParams = new StringBuffer("sensor=false");

        if (getState().language != null) {
            otherParams.append("&language=" + getState().language);
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
	public void repositioned(LatLon position) {
		repositionedRpc.repositioned(position);
	}
}
