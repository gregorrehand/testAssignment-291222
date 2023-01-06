package com.example.backend.service;

import com.example.backend.model.Sector;
import com.example.backend.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> getAll() {
        return sectorRepository.getSectorHierarchy();
    }

    public Sector save(Sector sector) {
        return sectorRepository.save(sector);
    }

    public Sector update(Sector sector, UUID id) {
        Sector existingRecord = sectorRepository.findById(id).get();

        if (Objects.nonNull(sector.getName()) && !"".equalsIgnoreCase(sector.getName())) {
            existingRecord.setName(sector.getName());
        }

        return sectorRepository.save(existingRecord);
    }
    public void delete(UUID id) {
        sectorRepository.deleteById(id);
    }
}
