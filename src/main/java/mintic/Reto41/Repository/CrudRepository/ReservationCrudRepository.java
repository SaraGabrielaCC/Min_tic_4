package mintic.Reto41.Repository.CrudRepository;

import mintic.Reto41.Entities.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
}
