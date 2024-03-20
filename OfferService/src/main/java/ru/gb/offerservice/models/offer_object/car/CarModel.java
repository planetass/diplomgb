package ru.gb.offerservice.models.offer_object.car;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "car_models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_model",nullable = false, unique = true)
    @Length(min = 2,max = 20)
    private String carModel;

    @Column(name = "cars")
    @OneToMany
    private List<Car> cars;


}
