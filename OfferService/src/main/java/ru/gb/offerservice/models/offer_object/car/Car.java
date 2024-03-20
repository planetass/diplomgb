package ru.gb.offerservice.models.offer_object.car;

import jakarta.persistence.*;
import lombok.*;
import ru.gb.offerservice.models.offer_object.OfferObject;


@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car extends OfferObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CarModel model;

    @ManyToOne
    private CarType carType;


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model=" + model +
                ", carType=" + carType +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userUID=" + userUID +
                ", objectStatus=" + objectStatus +
                ", objectType=" + objectType +
                '}';
    }
}
