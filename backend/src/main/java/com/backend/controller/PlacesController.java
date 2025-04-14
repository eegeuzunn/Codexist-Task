package com.backend.controller;

import com.backend.service.PlacesService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/places")
@Validated
public class PlacesController {

    private final PlacesService placesService;
    private final Logger logger = LoggerFactory.getLogger(PlacesController.class);

    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping
    public ResponseEntity<?> getNearbyPlaces(
            @RequestParam @NotNull @Min(value = -180) @Max(value = 180) Double longitude,
            @RequestParam @NotNull @Min(value = -90) @Max(value = 90) Double latitude,
            @RequestParam @NotNull @Min(value = 0) @Max(value = 50000) Double radius) {

        logger.info("Received request to get nearby places with longitude: {}, latitude: {}, radius: {}", longitude, latitude, radius);
        String result = placesService.getNearbyPlacesByLocation(longitude, latitude, radius);
        return ResponseEntity.ok(result);
    }

}
