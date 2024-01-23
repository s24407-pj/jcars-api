package edu.pjatk.jcarsapi.model.request.Reservation;

import com.fasterxml.jackson.annotation.JsonRootName;
import edu.pjatk.jcarsapi.model.Add;

import java.util.List;

public class ReservationRequest {
    private Reservation reservation;
    private List<Add> adds;


    public ReservationRequest() {
    }

    public ReservationRequest(Reservation reservation, List<Add> adds) {
        this.reservation = reservation;
        this.adds = adds;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public List<Add> getAdds() {
        return adds;
    }

    public void setAdds(List<Add> adds) {
        this.adds = adds;
    }
}
