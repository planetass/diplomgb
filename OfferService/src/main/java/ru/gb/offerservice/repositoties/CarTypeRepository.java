package ru.gb.offerservice.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.offerservice.models.offer_object.car.CarType;


@Repository
public interface CarTypeRepository extends JpaRepository<CarType,Long> {

}
