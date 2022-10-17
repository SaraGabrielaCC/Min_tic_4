package mintic.Reto41.Repository;

import mintic.Reto41.Entities.Reservation;
import mintic.Reto41.Repository.CrudRepository.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    public Optional<Reservation> getReservation(int id) {
        return reservationCrudRepository.findById(id);
    }
    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }

    public List<Reservation> getDatesReport(Date inicio,Date fin){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(inicio,fin);
    }
    public List<Reservation> getStatusReport(String sts){
        return reservationCrudRepository.findAllByStatus(sts);
    }

    public List<Object[]> getTopClients(){
        return reservationCrudRepository.getTopClients();
    }
}
