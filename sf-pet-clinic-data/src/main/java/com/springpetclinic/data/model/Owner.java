package com.springpetclinic.data.model;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true, exclude = {"pets"})
@EqualsAndHashCode(callSuper = true, exclude = {"pets"})

@Entity
@Table(name = "Owners")
public class Owner extends Person {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Column(name = "phone_number")
    private String phoneNumber;

    private String city;
    private String streetLine;

    @Builder
    private Owner(Long id, String firstName, String lastName, String phoneNumber, String city, String streetLine) {
        super(firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.streetLine = streetLine;
        setId(id);
    }

    public Pet getPet(String name, boolean ignoreNew) {
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                if (pet.getName().equalsIgnoreCase(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

}
