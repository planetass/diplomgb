package ru.gb.offerservice.models.offer_object.cargo;

import jakarta.persistence.*;
import lombok.*;
import ru.gb.offerservice.models.offer_object.OfferObject;

@Entity
@Table(name = "cargo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cargo extends OfferObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Integer weight;
    Integer length;
    Integer width;
    Integer height;


}
