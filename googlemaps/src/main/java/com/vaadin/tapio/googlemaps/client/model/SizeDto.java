package com.vaadin.tapio.googlemaps.client.model;

import java.io.Serializable;

/**
 * @author Jamel
 * @since 4/2/2014
 */
public class SizeDto implements Serializable {
	private double width;
	private double height;
	private String widthUnit;
	private String heightUnit;

	public SizeDto(double width, double height, String widthUnit, String heightUnit) {
		this.width = width;
		this.height = height;
		this.widthUnit = widthUnit;
		this.heightUnit = heightUnit;
	}

	public SizeDto(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public SizeDto(double width, double height, String widthUnit) {
		this.width = width;
		this.height = height;
		this.widthUnit = widthUnit;
	}

	public SizeDto() {
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		SizeDto sizeDto = (SizeDto) o;

		if (Double.compare(sizeDto.height, height) != 0) {
			return false;
		}
		if (Double.compare(sizeDto.width, width) != 0) {
			return false;
		}
		if (heightUnit != null ? !heightUnit.equals(sizeDto.heightUnit) : sizeDto.heightUnit != null) {
			return false;
		}
		if (widthUnit != null ? !widthUnit.equals(sizeDto.widthUnit) : sizeDto.widthUnit != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(width);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(height);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (widthUnit != null ? widthUnit.hashCode() : 0);
		result = 31 * result + (heightUnit != null ? heightUnit.hashCode() : 0);
		return result;
	}
}
