package com.vaadin.tapio.googlemaps.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.vaadin.shared.AbstractComponentState;
import com.vaadin.tapio.googlemaps.client.layers.GoogleMapKmlLayer;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolygon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import java.util.TreeMap;

/**
 * The shared state of the Google Maps. Contains also the default
 * 
 * @author Tapio Aali <tapio@vaadin.com>
 */
public class GoogleMapState extends AbstractGoogleMapState {
    private static final long serialVersionUID = 646346522643L;

    // defaults to the language setting of the browser
    public String mapTypeId = "Roadmap";
    public double zoom = 8.0;
    public double maxZoom = 21.0;
    public double minZoom = 0.0;

    public boolean draggable = true;
    public boolean keyboardShortcutsEnabled = true;
    public boolean scrollWheelEnabled = true;

    public Set<GoogleMapControl> controls = new HashSet<GoogleMapControl>(
            Arrays.asList(GoogleMapControl.MapType, GoogleMapControl.Pan,
                    GoogleMapControl.Rotate, GoogleMapControl.Scale,
                    GoogleMapControl.StreetView, GoogleMapControl.Zoom));

    public boolean limitCenterBounds = false;
    public LatLon centerSWLimit = new LatLon(0.0, 0.0);
    public LatLon centerNELimit = new LatLon(0.0, 0.0);

    public boolean limitVisibleAreaBounds = false;
    public LatLon visibleAreaSWLimit = new LatLon(0.0, 0.0);
    public LatLon visibleAreaNELimit = new LatLon(0.0, 0.0);

    public LatLon fitToBoundsNE = null;
    public LatLon fitToBoundsSW = null;

    public Set<GoogleMapPolygon> polygons = new HashSet<GoogleMapPolygon>();
    public Set<GoogleMapPolyline> polylines = new HashSet<GoogleMapPolyline>();
    public Set<GoogleMapKmlLayer> kmlLayers = new HashSet<GoogleMapKmlLayer>();

    public Map<Long, GoogleMapMarker> markers = new TreeMap<Long, GoogleMapMarker>();

    public Map<Long, GoogleMapInfoWindow> infoWindows = new HashMap<Long, GoogleMapInfoWindow>();

    public boolean isBusiness() {
        return clientId != null;
    }

}
