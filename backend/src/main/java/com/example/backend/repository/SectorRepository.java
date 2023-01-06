package com.example.backend.repository;

import com.example.backend.model.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SectorRepository
        extends CrudRepository<Sector, UUID>, CustomSectorRepository {
}