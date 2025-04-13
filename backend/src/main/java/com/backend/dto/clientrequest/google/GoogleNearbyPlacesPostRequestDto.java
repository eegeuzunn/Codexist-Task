package com.backend.dto.clientrequest.google;
import java.util.List;

/*
    Api istek yapısı bu şekilde olmalı. Buna uygun bir DTO yapısı oluşturuyoruz.

    '{
      "includedTypes": ["restaurant"],
      "maxResultCount": 10,
      "locationRestriction": {
        "circle": {
          "center": {
            "latitude": 37.7937,
            "longitude": -122.3965},
          "radius": 500.0
        }
      }
    }

 */
public record GoogleNearbyPlacesPostRequestDto(
        List<String> includedTypes,
        Integer maxResultCount,
        LocationRestriction locationRestriction
) {
}




