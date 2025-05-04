package com.backend.service;

import com.backend.client.GooglePlacesApiClient;
import com.backend.model.Places;
import com.backend.model.PlacesId;
import com.backend.repository.PlacesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
class PlacesServiceTest {

    private GooglePlacesApiClient googlePlacesApiClient;
    private PlacesRepository placesRepository;

    private PlacesService placesService;

    @BeforeEach
    void setUp() {

        googlePlacesApiClient = Mockito.mock(GooglePlacesApiClient.class);
        placesRepository = Mockito.mock(PlacesRepository.class);

        placesService = new PlacesService(googlePlacesApiClient, placesRepository);
    }

    @AfterEach
    void tearDown() {
        googlePlacesApiClient = null;
        placesRepository = null;
        placesService = null;
    }


    @Test
    void getNearbyPlacesByLocation_shouldReturnCachedResult_whenPlacesExistInDatabase(){

        // Test verileri
        double longitude = 10.0;
        double latitude = 20.0;
        double radius = 30.0;

        PlacesId placesId = new PlacesId(longitude, latitude, radius);
        Optional<Places> places = Optional.of(new Places(placesId, "cached result"));

        String expectedResult = "cached result";

        // Repositorynin davranışının belirlenmesi
        Mockito.when(placesRepository.findById(placesId)).thenReturn(places);

        // Metodun çağrılması
        String result = placesService.getNearbyPlacesByLocation(longitude, latitude, radius);

        // Sonuçların doğrulanması
        assertEquals(expectedResult, result);

        // Repositorynin çağrıldığını doğrulama
        Mockito.verify(placesRepository).findById(placesId);
    }

    @Test
    void getNearbyPlacesByLocation_shouldReturnResultFromGooglePlacesApiCall_whenPlacesNotExistInDatabase(){

        // Test verileri

        double longitude = 10.0;
        double latitude = 20.0;
        double radius = 30.0;

        PlacesId placesId = new PlacesId(longitude, latitude, radius);
        Optional<Places> places = Optional.empty();

        String expectedResult = "api result";

        // Çağrılan fonksiyonların davranışının belirlenmesi
        Mockito.when(placesRepository.findById(placesId)).thenReturn(places);
        Mockito.when(googlePlacesApiClient.getNearbyPlaces(longitude, latitude, radius)).thenReturn(expectedResult);

        // Metodun çağrılması
        String result = placesService.getNearbyPlacesByLocation(longitude, latitude, radius);

        // Sonuçların doğrulanması
        assertEquals(expectedResult, result);

        // fonksiyonların çağrıldığını doğrulama
        Mockito.verify(placesRepository).findById(placesId);
        Mockito.verify(googlePlacesApiClient).getNearbyPlaces(longitude, latitude, radius);
        Mockito.verify(placesRepository).save(new Places(placesId, result));


    }

}













