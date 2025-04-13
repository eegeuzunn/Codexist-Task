package com.backend.repository;

import com.backend.model.Places;
import com.backend.model.PlacesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacesRepository extends JpaRepository<Places, PlacesId> {
}
