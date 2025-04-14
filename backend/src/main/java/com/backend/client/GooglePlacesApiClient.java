package com.backend.client;

import com.backend.dto.clientrequest.google.Circle;
import com.backend.dto.clientrequest.google.GoogleNearbyPlacesPostRequestDto;
import com.backend.dto.clientrequest.google.Location;
import com.backend.dto.clientrequest.google.LocationRestriction;
import com.backend.exception.GooglePlacesApiDownException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GooglePlacesApiClient {

    @Value("${GOOGLE_API_KEY}")
    private String ApiKey;

    private final RestClient restClient;
    private final Logger logger = LoggerFactory.getLogger(GooglePlacesApiClient.class);

    public GooglePlacesApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getNearbyPlaces(Double longitude, Double latitude, Double radius) {

        logger.info("Calling Google Places API with longitude: {}, latitude: {}, radius: {}", longitude, latitude, radius);
        String result = restClient.post()
                .uri("https://places.googleapis.com/v1/places:searchNearby")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Goog-FieldMask",
                        "places.displayName," +
                                "places.internationalPhoneNumber," +
                                "places.formattedAddress," +
                                "places.shortFormattedAddress," +
                                "places.websiteUri," +
                                "places.googleMapsUri," +
                                "places.location")
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
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    logger.error("Google Places API is down, received 5xx error: {}", response.getStatusCode());
                    throw new GooglePlacesApiDownException("Service is down due to google places api");
                })
                .body(String.class);

        logger.info("Received response from Google Places API");
        return result;
    }
}
