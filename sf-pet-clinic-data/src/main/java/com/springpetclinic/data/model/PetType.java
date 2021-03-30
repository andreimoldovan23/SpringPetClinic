package com.springpetclinic.data.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "PetTypes")
public class PetType extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Builder
    private PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
