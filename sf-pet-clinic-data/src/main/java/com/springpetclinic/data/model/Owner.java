package com.springpetclinic.data.model;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true, exclude = {"pets", "address"})
@EqualsAndHashCode(callSuper = true, exclude = {"pets", "address"})

@Entity
@Table(name = "Owners")
public class Owner extends Person {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Column(name = "phone_number")
    private String phoneNumber = null;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address = null;

    @Builder
    private Owner(String firstName, String lastName, String phoneNumber, Address address) {
        super(firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
