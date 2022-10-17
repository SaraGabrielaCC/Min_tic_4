package mintic.Reto41.Service;

import mintic.Reto41.Controller.DTOs.StatusAccount;
import mintic.Reto41.Controller.DTOs.TopClients;
import mintic.Reto41.Entities.Client;
import mintic.Reto41.Entities.Reservation;
import mintic.Reto41.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int reservationId) {
        return reservationRepository.getReservation(reservationId);
    }

    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation()==null){
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservation> e= reservationRepository.getReservation(reservation.getIdReservation());
            if(e.isPresent()){
                return reservationRepository.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= reservationRepository.getReservation(reservation.getIdReservation());
            if(e.isPresent()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation (int id){
        Boolean d = getReservation(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return d;
    }

    public List<Reservation> getReservationsByPeriod(String dateA,String dateB){

        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date a= new Date();
        Date b=new Date();
        try {
            a=parser.parse(dateA);
            b=parser.parse(dateB);
        }catch (ParseException e){
            e.printStackTrace();;
        }
        if(a.before(b)){
            return reservationRepository.getDatesReport(a,b);
        }else{
            return new ArrayList<Reservation>();
        }
    }
    public StatusAccount getReportByStatus(){
        List<Reservation> completes=reservationRepository.getStatusReport("completed");
        List<Reservation> cancelled=reservationRepository.getStatusReport("cancelled");

        StatusAccount resultado=new StatusAccount(completes.size(),cancelled.size());
        return resultado;

    }
    public List<TopClients> getTopclients(){
        List<TopClients> tc=new ArrayList<>();
        List<Object[]> result= reservationRepository.getTopClients();

        for(int i=0;i<result.size();i++){
            int total=Integer.parseInt(result.get(i)[1].toString());
            Client client= (Client) result.get(i)[0];
            TopClients topClient=new TopClients(total,client);
            tc.add(topClient);
        }
        return tc;
    }


}
