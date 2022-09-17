package menu_and_menu_items;

import entities.User;
import exceptions.booking_menu_exceptions.NoSuchFlightException;

import java.util.Optional;

public class DisplayFlight extends MenuItem {
    private User user;
    public DisplayFlight(int id) {
        super(id);
    }

    public DisplayFlight(int id, User user) {
        super(id);
        this.user = user;
    }

    @Override
    public void run() {
        int flightIdInput = getFlightIdInput();
        logSearching(flightIdInput);
        getDatabase().getFcInMemory().displayFlight(flightIdInput, getConsole());
    }

    public Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

    private int getFlightIdInput() {
        return getConsole().getPositiveInt("Please enter the id of the flight:");
    }

    private void logSearching(int flightId) {
        if (getUser().isPresent()) {
            getLogger().searchInfo(this.user, flightId);
        }
    }
}
