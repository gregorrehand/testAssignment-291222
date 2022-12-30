package com.example.backend.controller;

import java.util.List;
import java.util.UUID;

import com.example.backend.model.Sector;
import com.example.backend.service.SectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SectorController {

    @Autowired private SectorService sectorService;

    @GetMapping("/sectors")
    public List<Sector> getSectors()
    {
        return sectorService.getAll();
    }

    @PostMapping("/sectors")
    public Sector saveSector(
            @Valid @RequestBody Sector sector)
    {

        return sectorService.save(sector);
    }

    @PutMapping("/sectors/{id}")
    public Sector updateSector(@RequestBody Sector sector,
                     @PathVariable("id") UUID sectorId)
    {
        return sectorService.update(sector, sectorId);
    }
    @DeleteMapping("/sectors/{id}")
    public String deleteSector(@PathVariable("id")
                                       UUID sectorId)
    {
        sectorService.delete(sectorId);
        return "Deleted Successfully";
    }
}