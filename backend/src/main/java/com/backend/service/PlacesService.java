package com.backend.service;

import com.backend.client.GooglePlacesApiClient;
import com.backend.model.Places;
import com.backend.model.PlacesId;
import com.backend.repository.PlacesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacesService {

    private final GooglePlacesApiClient googlePlacesApiClient;
    private final PlacesRepository placesRepository;

    public PlacesService(GooglePlacesApiClient googlePlacesApiClient, PlacesRepository placesRepository) {
        this.googlePlacesApiClient = googlePlacesApiClient;
        this.placesRepository = placesRepository;
    }

    public String getNearbyPlacesByLocation(Double longitude, Double latitude, Double radius) {

        PlacesId id = new PlacesId(longitude, latitude, radius);
        Optional<Places> places = placesRepository.findById(id);

        // Databasede var mı diye kontrol ediyoruz. Yoksa Places Api çaprısı yapıyoruz ve gelen sonucu kaydediyoruz.
        if(places.isPresent()){
            System.out.println("Places found in database");
            return places.get().getResult();
        } else{
            System.out.println("Places not found in database, calling Google Places API");
            String apiCallResult = googlePlacesApiClient.getNearbyPlaces(longitude, latitude, radius);
            placesRepository.save(new Places(new PlacesId(longitude, latitude, radius), apiCallResult));
            return apiCallResult;
        }
    }
}
