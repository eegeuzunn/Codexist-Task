package com.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import static org.hibernate.Length.LONG32;

@Entity
public class Places {

    //Normalde düz id verip jpa sorgusunda değerler ile aratabilirdik daha kolay olurdu. Burda biraz daha işi değiştirmek
    //ve aslında senaryomuza uyuyor diye composite key kullandım.
    @EmbeddedId
    private PlacesId placesId;


    @Column(length=LONG32)
    private String result;

    public Places() {
    }

    public Places(PlacesId placesId, String result) {
        this.placesId = placesId;
        this.result = result;
    }

    public PlacesId getPlacesId() {
        return placesId;
    }

    public void setPlacesId(PlacesId placesId) {
        this.placesId = placesId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
