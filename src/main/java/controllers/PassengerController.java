package controllers;

import entities.Booking;
import entities.Passenger;
import services.PassengerService;

import java.util.List;
import java.util.Optional;

public class PassengerController {
    private PassengerService ps;

    public PassengerController(PassengerService ps) {
        this.ps = ps;
    }

    public PassengerController() {
        ps = new PassengerService();
    }

    public PassengerService getService() {
        return ps;
    }

    public Optional<List<Passenger>> getAllPassengers() {
        return ps.getAllPassengers();
    }

    public void setAllPassengers(List<Passenger> data) {
        ps.setAllPassengers(data);
    }
}
