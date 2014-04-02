package com.vaadin.tapio.googlemaps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.maps.gwt.client.*;
import com.vaadin.tapio.googlemaps.client.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamel
 * @since 4/2/2014
 */
public class DtoMapper {

	public static final Mapper<LatLon, LatLng> LAT_LNG_MAPPER;
	public static final Mapper<SizeDto, Size> SIZE_MAPPER;
	public static final Mapper<StreetViewStatusDto, StreetViewStatus> STREET_VIEW_STATUS_MAPPER;
	public static final Mapper<StreetViewLinkDto, StreetViewLink> STREET_VIEW_LINK_MAPPER;
	public static final Mapper<StreetViewLocationDto, StreetViewLocation> STREET_VIEW_LOCATION_MAPPER;
	public static final Mapper<StreetViewTileDataDto, StreetViewTileData> STREET_VIEW_TILE_DATA_MAPPER;
	public static final Mapper<StreetViewPanoramaDataDto, StreetViewPanoramaData> STREET_VIEW_PANORAMA_DATA_MAPPER;

	static {

		LAT_LNG_MAPPER = new Mapper<LatLon, LatLng>() {
			@Override
			public LatLon map(LatLng data) {
				if (data == null)
					return null;

				return new LatLon(data.lat(), data.lng());
			}
		};

		SIZE_MAPPER = new Mapper<SizeDto, Size>() {
			@Override
			public SizeDto map(Size data) {
				if (data == null)
					return null;

				return new SizeDto(data.getWidth(), data.getHeight());
			}
		};

		STREET_VIEW_STATUS_MAPPER = new Mapper<StreetViewStatusDto, StreetViewStatus>() {
			@Override
			public StreetViewStatusDto map(StreetViewStatus data) {
				if (data == null) return null;

				return StreetViewStatusDto.getByString(data.getValue());
			}
		};

		STREET_VIEW_LINK_MAPPER = new Mapper<StreetViewLinkDto, StreetViewLink>() {
			@Override
			public StreetViewLinkDto map(StreetViewLink data) {
				if (data == null)
					return null;

				StreetViewLinkDto dto = new StreetViewLinkDto();
				dto.setDescription(data.getDescription());
				dto.setHeading(data.getHeading());
				dto.setPano(data.getPano());
				return dto;
			}
		};

		STREET_VIEW_LOCATION_MAPPER = new Mapper<StreetViewLocationDto, StreetViewLocation>() {
			@Override
			public StreetViewLocationDto map(StreetViewLocation data) {
				if (data == null)
					return null;

				StreetViewLocationDto dto = new StreetViewLocationDto();
				dto.setPano(data.getPano());
				dto.setDescription(data.getPano());
				dto.setLatLon(LAT_LNG_MAPPER.map(data.getLatLng()));
				return dto;
			}
		};

		STREET_VIEW_TILE_DATA_MAPPER = new Mapper<StreetViewTileDataDto, StreetViewTileData>() {
			@Override
			public StreetViewTileDataDto map(StreetViewTileData data) {
				if (data == null)
					return null;

				StreetViewTileDataDto dto = new StreetViewTileDataDto();
				dto.setCenterHeading(data.getCenterHeading());
				dto.setTileSize(SIZE_MAPPER.map(data.getTileSize()));

				// Not going to support since this is a query function, not sure what to do in this case.
				// dto.setTileUrl(data.getTileUrl());

				dto.setWorldSize(SIZE_MAPPER.map(data.getWorldSize()));
				return dto;
			}
		};

		STREET_VIEW_PANORAMA_DATA_MAPPER = new Mapper<StreetViewPanoramaDataDto, StreetViewPanoramaData>() {
			@Override
			public StreetViewPanoramaDataDto map(StreetViewPanoramaData data) {
				if (data == null)
					return null;

				StreetViewPanoramaDataDto dto = new StreetViewPanoramaDataDto();
				dto.setCopyright(data.getCopyright());
				dto.setImageDate(data.getImageDate());
				dto.setStreetViewLinks(mapList(data.getLinks(), STREET_VIEW_LINK_MAPPER));
				dto.setStreetViewLocation(STREET_VIEW_LOCATION_MAPPER.map(data.getLocation()));
				dto.setStreetViewTileData(STREET_VIEW_TILE_DATA_MAPPER.map(data.getTiles()));
				return dto;
			}
		};
	}

	public static <DtoType extends Serializable, DataType extends JavaScriptObject> List<DtoType> mapList(
		JsArray<DataType> jsArray, Mapper<DtoType, DataType> function) {
		List<DtoType> dtos = new ArrayList<DtoType>();

		for (int i = 0; i < jsArray.length(); i++)
			dtos.add(function.map(jsArray.get(i)));

		return dtos;
	}

	public interface Mapper<DtoType extends Serializable, DataType extends JavaScriptObject> {
		DtoType map(DataType data);
	}
}
