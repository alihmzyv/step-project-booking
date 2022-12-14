package entities;

import database.Database;
import helpers.Helper;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Flight implements Identifiable, Serializable{
    @Serial
    private static final long serialVersionUID = 9203702769697625107L;
    private static int idCounter;

    private final int id;
    private final String flightDesignator;
    private final Airline airline;
    private final Airport from;
    private final Airport to;
    private LocalDateTime dateTimeOfDeparture;
    private final LocalDateTime dateTimeOfLanding;
    private final Duration flightDuration;
    private int capacity;
    private final List<Passenger> passengers;


    static {
        idCounter = Database.getIdCounter("Flight");
    }


    //constructors
    public Flight(Airline airline, Airport from, Airport to,
                  LocalDateTime dateTimeOfDeparture, LocalDateTime dateTimeOfLanding,
                  int capacity) {
        this.id = idCounter++;
        this.airline = airline;
        this.from = from;
        this.to = to;
        this.dateTimeOfDeparture = dateTimeOfDeparture;
        this.dateTimeOfLanding = dateTimeOfLanding;
        this.flightDuration = Duration.between(dateTimeOfDeparture, dateTimeOfLanding);
        this.capacity = capacity;
        this.flightDesignator = generateDesignator();
        this.passengers = new ArrayList<>();
    }


    //getter and setters
    public static int getIdCounter() {
        return idCounter;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public Airline getAirline() {
        return airline;
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public LocalDateTime getDateTimeOfDeparture() {
        return dateTimeOfDeparture;
    }

    public LocalDate getDateOfDeparture() {
        return dateTimeOfDeparture.toLocalDate();
    }

    public LocalDateTime getDateTimeOfLanding() {
        return dateTimeOfLanding;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDateTimeOfDeparture(LocalDateTime dateTimeOfDeparture) {
        this.dateTimeOfDeparture = dateTimeOfDeparture;
    }


    //methods
    //static methods
    /*
     * returns a random Flight whose:
     * * dateTimeOfDeparture is between current date-time with added "earlies" amount (inc.) and
     *   current date-time added "latest" amount (inc.)
     * * flightDuration is randomly selected to be between 60 (inc.) and 300 (inc.) minutes
     * * airline is randomly selected
     * * from and to are both randomly chosen, being not the same
     * * capacity is randomly selected to be between 30 (inc.) and 100 (inc.)
     */
    public static Flight getRandom(int earliest, int latest, ChronoUnit unit) {
        Random rnd = new Random();
        LocalDateTime dateTimeOfDeparture = LocalDateTime.now().plus(Duration.of(rnd.nextInt(earliest, latest + 1), unit));
        LocalDateTime dateTimeOfLanding = dateTimeOfDeparture.plus(Duration.ofMinutes(rnd.nextInt(60, 301)));
        Airline airline = Airline.getRandom();
        Airport from = Airport.getRandom();
        Airport to = Airport.getRandomExcept(from);
        int capacity = rnd.nextInt(30, 101);
        return new Flight(airline, from, to, dateTimeOfDeparture, dateTimeOfLanding, capacity);
    }

    /*
     * returns a modifiable List of random Flights of specified length,
     * generated by calling Flight.getRandom(int earliest, int latest, ChronoUnit unit)
     * * count - the size of the list to be returned
     */
    public static List<Flight> getRandom(int count, int earliest, int latest, ChronoUnit unit) {
        return IntStream.range(0, count)
                .mapToObj(i -> getRandom(earliest, latest, unit))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //instance methods
    /*
     * adds the specified Passenger to the passengers List.
     */
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    /*
     * removes the specified Passenger from the passengers List.
     */
    public boolean removePassenger(Passenger passenger) {
        return passengers.remove(passenger);
    }

    /*
     * Returns true if the passengers list of this flight contains the specified Passenger, otherwise false.
     */
    public boolean hasPassenger(Passenger passenger) {
        return passengers.contains(passenger);
    }

    /*
     * Decreases the capacity of this flight by 1
     */
    public void decrementCapacity() {
        setCapacity(this.capacity - 1);
    }

    /*
     * Increases the capacity of this flight by 1
     */
    public void incrementCapacity() {
        setCapacity(this.capacity + 1);
    }

    /*
     * Returns a String of length 6 whose alphabetic part represents the IATA designator of the airline of this flight,
     * while the numerical part is obtained by hashing from, to, and capacity of this flight.
     */
    public String generateDesignator() {
        return String.format("%s%d", this.airline.getIATADesignator(),
                        Math.abs(Objects.hash(from, to, capacity)))
                .substring(0, 6);
    }

    //equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.join(" || ",
                String.valueOf(id),
                flightDesignator,
                from.toString(), to.toString(),
                dateTimeOfDeparture.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)),
                dateTimeOfLanding.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)),
                Helper.getHumanReadableDuration(flightDuration));
    }
}
