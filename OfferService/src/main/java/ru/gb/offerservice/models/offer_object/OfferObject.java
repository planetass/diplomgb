package ru.gb.offerservice.models.offer_object;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.rmi.server.UID;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class OfferObject {

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "description", nullable = false)
    protected String description;

    @Column(name = "UID", nullable = false)
    protected UUID userUID;

    @Column(name = "object_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    protected ObjectStatus objectStatus;

    @Column(name = "object_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    protected ObjectType objectType;




}
