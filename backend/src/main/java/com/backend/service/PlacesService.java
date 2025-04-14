package com.backend.service;

import com.backend.client.GooglePlacesApiClient;
import com.backend.model.Places;
import com.backend.model.PlacesId;
import com.backend.repository.PlacesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacesService {

    private final GooglePlacesApiClient googlePlacesApiClient;
    private final PlacesRepository placesRepository;
    private final Logger logger = LoggerFactory.getLogger(PlacesService.class);

    public PlacesService(GooglePlacesApiClient googlePlacesApiClient, PlacesRepository placesRepository) {
        this.googlePlacesApiClient = googlePlacesApiClient;
        this.placesRepository = placesRepository;
    }

    public String getNearbyPlacesByLocation(Double longitude, Double latitude, Double radius) {

        PlacesId id = new PlacesId(longitude, latitude, radius);
        Optional<Places> places = placesRepository.findById(id);

        // Databasede var mı diye kontrol ediyoruz. Yoksa Places Api çaprısı yapıyoruz ve gelen sonucu kaydediyoruz.
        if(places.isPresent()){
            logger.info("Places found in database, returning from cache for [longitude: {}, latitude: {}, radius: {}]", longitude, latitude, radius);
            return places.get().getResult();
        } else{
            logger.info("Places not found in database, calling Google Places API for [longitude: {}, latitude: {}, radius: {}]", longitude, latitude, radius);
            String apiCallResult = googlePlacesApiClient.getNearbyPlaces(longitude, latitude, radius);
            placesRepository.save(new Places(new PlacesId(longitude, latitude, radius), apiCallResult));
            return apiCallResult;
        }
    }
}
