package com.springpetclinic.data.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true, exclude = {"pet"})
@EqualsAndHashCode(callSuper = true, exclude = {"pet"})

@Entity
@Table(name = "Visits")
public class Visit extends BaseEntity {

    @Column(name = "time")
    private LocalDateTime date;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Builder
    private Visit(Long id, String description, LocalDateTime date, Pet pet) {
        super(id);
        this.description = description;
        this.date = date;
        this.pet = pet;
    }

}
