package mintic.Reto41.Repository;

import mintic.Reto41.Controller.DTOs.TopClients;
import mintic.Reto41.Entities.Client;
import mintic.Reto41.Entities.Reservation;
import mintic.Reto41.Repository.CrudRepository.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public List<TopClients> getTopClients(){
        List<TopClients> res= new ArrayList<>();
        List<Object[]> report= reservationCrudRepository.getTopClients();
        for(int i=0; i < report.size(); i++){
            res.add(new TopClients((Long)report.get(i)[1], (Client) report.get(i)[0]));
        }
        return res;

    }
}
