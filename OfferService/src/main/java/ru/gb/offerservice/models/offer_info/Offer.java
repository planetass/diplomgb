package ru.gb.offerservice.models.offer_info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;
import ru.gb.offerservice.models.offer_object.ObjectType;
import ru.gb.offerservice.models.offer_type.OfferType;

import java.rmi.server.UID;
import java.util.Date;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    Date createDate;

    @Column(name = "expired_date")
    Date expiredDate;

    @Column(name = "name", nullable = false)
    @Length(min = 20,max = 50)
    private String name;

    @Column(name = "description", nullable = false)
    @Length(min = 20,max = 1000)
    private String description;

    @Column(name = "offer_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OfferType offerType;

    @Column(name = "object_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ObjectType objectType;

    @Column(name = "object_id", nullable = false)
    private Long objectId;

    @Column(name = "UID", nullable = false)
    private  UID userUID;

}
