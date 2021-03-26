package com.springpetclinic.data.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@ToString(callSuper = true, exclude = {"pet"})
@EqualsAndHashCode(callSuper = true, exclude = {"pet"})

@Entity
@Table(name = "Visits")
public class Visit extends BaseEntity {

    @Column(name = "time")
    private LocalDateTime localDateTime = null;

    @Lob
    @Column(name = "description")
    private String description = null;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet = null;

}
