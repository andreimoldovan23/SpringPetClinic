package com.springpetclinic.data.model;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true, exclude = {"specialties"})
@EqualsAndHashCode(callSuper = true, exclude = {"specialties"})

@Entity
@Table(name = "Vets")
public class Vet extends Person {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "VetsSpecialties",
        joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<VetSpecialty> specialties = new HashSet<>();

    @Builder
    private Vet(Long id, String firstName, String lastName) {
        super(firstName, lastName);
        setId(id);
    }

}
