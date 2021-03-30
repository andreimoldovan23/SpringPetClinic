package com.springpetclinic.data.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true, exclude = {"visits", "owner", "type"})
@EqualsAndHashCode(callSuper = true, exclude = {"visits", "owner", "type"})

@Entity
@Table(name = "Pets")
public class Pet extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    @Builder
    private Pet(Long id, PetType type, Owner owner, LocalDate birthDate, String name) {
        super(id);
        this.type = type;
        this.owner = owner;
        this.birthDate = birthDate;
        this.name = name;
    }
}
