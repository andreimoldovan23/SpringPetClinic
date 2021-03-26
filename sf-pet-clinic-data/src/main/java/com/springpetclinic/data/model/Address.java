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
@Table(name = "Addresses")
public class Address extends BaseEntity {

    @Column(name = "city")
    private String city = null;

    @Column(name = "street")
    private String streetLine = null;

}
