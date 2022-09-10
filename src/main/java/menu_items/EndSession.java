package menu_items;

import database.Database;
import io.Console;

public class EndSession extends MenuItem implements Runnable {


    public EndSession(int id) {
        super(id);
    }

    @Override
    public void run() {
        getDatabase().update();
    }
}
