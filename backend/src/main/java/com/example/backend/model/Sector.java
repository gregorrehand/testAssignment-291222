package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "sector")
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "Sector name may not be empty")
    @Size(min = 2, max = 128, message = "Sector name must be between 2 and 128 characters long")
    @Column(name = "sector_name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name="parent_sector_id")
    private Sector parentSector;

    @OneToMany(mappedBy = "parentSector")
    private Set<Sector> subSectors = new HashSet<>();
}
