package com.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.util.Objects;

@Embeddable
public class PlacesId {

    private Double longitude;
    private Double latitude;
    private Double radius;

    public PlacesId() {
    }

    public PlacesId(Double longitude, Double latitude, Double radius) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlacesId placesId = (PlacesId) o;
        return Objects.equals(longitude, placesId.longitude) && Objects.equals(latitude, placesId.latitude) && Objects.equals(radius, placesId.radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, radius);
    }
}
