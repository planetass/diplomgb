package ru.gb.offerservice.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.offerservice.models.offer_object.car.CarModel;

public interface CarModelsRepository extends JpaRepository<CarModel,Long> {

}
