package com.springpetclinic.data.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Specialties")
public class VetSpecialty extends BaseEntity {

    @Column(name = "name")
    private String specialtyName = null;

}
