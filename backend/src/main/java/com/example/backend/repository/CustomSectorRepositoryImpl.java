package com.example.backend.repository;

import com.example.backend.model.Sector;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.*;

public class CustomSectorRepositoryImpl implements CustomSectorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //Fetches all sectors without a parent and populates their children and children's children and so on
    @Override
    public List<Sector> getSectorHierarchy() {
        String sql = "WITH RECURSIVE cte AS ( " +
                "SELECT id, sector_name, parent_sector_id " +
                "FROM sector " +
                "WHERE parent_sector_id IS NULL " +
                "UNION ALL " +
                "SELECT s.id, s.sector_name, s.parent_sector_id " +
                "FROM sector s " +
                "INNER JOIN cte ON s.parent_sector_id = cte.id " +
                ") " +
                "SELECT * FROM cte";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultList = query.getResultList();
        List<Sector> sectors = new ArrayList<>();
        Map<UUID, Sector> sectorMap = new HashMap<>();
        for (Object[] row : resultList) {
            UUID id = (UUID) row[0];
            String name = (String) row[1];
            UUID parentId = (UUID) row[2];
            Sector sector = sectorMap.get(id);
            if (sector == null) {
                sector = new Sector();
                sector.setId(id);
                sector.setName(name);
                sectorMap.put(id, sector);
            } else if (sector.getName() == null) { //If a child of the sector was processed before the sector itself, the sector exists in sectorMap but without a name
                sector.setName(name);
            }
            if (parentId != null) {
                addChildToParent(sectorMap, parentId, sector);
            } else {
                sectors.add(sector);
            }
        }
        return sectors;
    }

    private static void addChildToParent(Map<UUID, Sector> sectorMap, UUID parentId, Sector sector) {
        Sector parent = sectorMap.get(parentId);
        if (parent == null) {
            parent = new Sector();
            parent.setId(parentId);
            sectorMap.put(parentId, parent);
        }
        Set<Sector> subSectors = parent.getSubSectors();
        subSectors.add(sector);
        parent.setSubSectors(subSectors);
    }
}