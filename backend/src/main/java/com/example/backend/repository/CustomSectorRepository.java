package com.example.backend.repository;

import com.example.backend.model.Sector;
import java.util.List;

public interface CustomSectorRepository {
    //Fetches all sectors without a parent and populates their children and children's children and so on
    List<Sector> getSectorHierarchy();
}
