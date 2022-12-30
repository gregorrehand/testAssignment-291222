package com.example.backend.devtools;

import com.example.backend.repository.SectorRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


import com.example.backend.model.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements ApplicationRunner {
    private SectorRepository sectorRepository;

    @Autowired
    public DataInitializer(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public void run(ApplicationArguments args) {
        if (sectorRepository.findAll().spliterator().getExactSizeIfKnown() == 0) {
            InitializeDefaultSectorsData();
        }
    }
    public void InitializeDefaultSectorsData() {
        List<Sector> latestParents = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader( new InputStreamReader(getClass().getClassLoader().getResourceAsStream("InitialData.txt"), StandardCharsets.UTF_8));
            String line = reader.readLine();

            while (line != null) {
                String[] splitLine = line.split("#");
                Sector sectorToSave = new Sector();
                sectorToSave.setName(splitLine[splitLine.length - 1]);
                if (splitLine.length > 1) {
                    sectorToSave.setParentSector(latestParents.get(splitLine.length - 2));
                }
                Sector savedSector = sectorRepository.save(sectorToSave);
                if (latestParents.size() < splitLine.length) {
                    latestParents.add(savedSector);
                } else {
                    latestParents.set(splitLine.length - 1, savedSector);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
