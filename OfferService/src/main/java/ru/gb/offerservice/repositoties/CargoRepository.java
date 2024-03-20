package ru.gb.offerservice.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.offerservice.models.offer_object.cargo.Cargo;

public interface CargoRepository extends JpaRepository<Cargo,Long> {

}
