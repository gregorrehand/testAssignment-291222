package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 128, message = "Name must be between 2 and 128 characters long")
    @Column(name = "reply_name", nullable = false)
    private String name;
    @Column(name = "agree_to_terms", nullable = false)
    private boolean agreeToTerms;

    @NotEmpty(message = "Please select at least 1 sector")
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "reply_sector",
            joinColumns = { @JoinColumn(name = "reply_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "sector_id", referencedColumnName = "id") })
    private Set<Sector> sectors;
}
