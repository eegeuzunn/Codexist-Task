package com.backend.controller;

import com.backend.service.PlacesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/places")
public class PlacesController {

    private final PlacesService placesService;

    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping
    public ResponseEntity<?> getNearbyPlaces(@RequestParam Double longitude, @RequestParam Double latitude, @RequestParam Double radius) {
        String result = placesService.getNearbyPlacesByLocation(longitude, latitude, radius);
        return ResponseEntity.ok(result);
    }

}
