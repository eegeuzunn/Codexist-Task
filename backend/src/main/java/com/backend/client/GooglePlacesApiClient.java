package com.backend.client;

import com.backend.dto.clientrequest.google.Circle;
import com.backend.dto.clientrequest.google.GoogleNearbyPlacesPostRequestDto;
import com.backend.dto.clientrequest.google.Location;
import com.backend.dto.clientrequest.google.LocationRestriction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GooglePlacesApiClient {

    @Value("${GOOGLE_API_KEY}")
    private String ApiKey;

    private final RestClient restClient;


    public GooglePlacesApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getNearbyPlaces(Double longitude, Double latitude, Double radius) {
        String result = restClient.post()
                .uri("https://places.googleapis.com/v1/places:searchNearby")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Goog-FieldMask",
                        "places.displayName," +
                                "places.internationalPhoneNumber," +
                                "places.formattedAddress," +
                                "places.shortFormattedAddress," +
                                "places.websiteUri," +
                                "places.googleMapsUri," )
                .header("X-Goog-Api-Key", ApiKey)
                .body(
                        new GoogleNearbyPlacesPostRequestDto(
                        null,
                        20,
                            new LocationRestriction(
                                    new Circle(
                                            new Location(latitude, longitude),
                                            radius
                                    )
                            )
                        )
                )
                .retrieve()
                .body(String.class);
        return result;
    }
}
