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
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true, exclude = {"visits", "owner", "type"})
@EqualsAndHashCode(callSuper = true, exclude = {"visits", "owner", "type"})

@Entity
@Table(name = "Pets")
public class Pet extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private PetType type = null;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner = null;

    @Column(name = "birth_date")
    private LocalDate birthDate = null;

    @Column(name = "name")
    private String name = null;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Pet(PetType type, Owner owner, LocalDate birthDate, String name) {
        this.type = type;
        this.owner = owner;
        this.birthDate = birthDate;
        this.name = name;
    }
}
