package ru.gb.offerservice.models.offer_object.car;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "car_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_type",nullable = false, unique = true)
    @Length(min = 5,max = 30)
    private String carType;

    @Column(name = "cars")
    @OneToMany
    private List<Car> cars;
}
