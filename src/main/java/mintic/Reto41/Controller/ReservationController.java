package mintic.Reto41.Controller;

import mintic.Reto41.Controller.DTOs.StatusAccount;
import mintic.Reto41.Controller.DTOs.TopClients;
import mintic.Reto41.Entities.Reservation;
import mintic.Reto41.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getReservations() {
        return reservationService.getAll();
    }


    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int reservationId) {
        return reservationService.getReservation(reservationId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return reservationService.deleteReservation(id);
    }
    @GetMapping("/report-dates/{dateA}/{dateB}")
    public List<Reservation> getByDates(@PathVariable("dateA")String da,@PathVariable("dateB")String db ){
        return reservationService.getReservationsByPeriod(da,db);
    }
    @GetMapping("/report-status")
    public StatusAccount getByStatus(){
        return reservationService.getReportByStatus();
    }
    @GetMapping("/report-clients")
    public List<TopClients> getTopClients(){
        return reservationService.getTopclients();
    }

}
