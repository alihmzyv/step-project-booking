Step Project (Console Application) At The End Of Java Core Module at ABB Tech Academy Java Backend Program

*** IN ORDER SAVE CHANGES TO THE LOCAL DATABASE, YOU SHOULD ALWAYS LEAVE IN THE FOLLOWING WAY -> 9 - END SESSION, 7 - EXIT.
OTHERWISE REGISTRATION, BOOKING, CANCEL BOOKING ETC. OPERATIONS WILL BE NOT BE SAVED LOCALLY SINCE THE LOCAL DATABASE
IS ONLY UPDATED WHEN 7 - EXIT IS CHOSEN (FOR THE SAKE OF PERFORMANCE) ***

To run the app, compile Main.java (src/main/java/Main.java) and run Main.class.
To continue with any of the menu items, you can enter the number of the menu item.
e.g., to register, type "2" and press enter button.
You can find the daily activities of users in "logs/users_daily_activities" path. (As soon as you run Main class, the
directories will appear).

                                        MAIN MENU:
1 - LOGIN - if you have registered (2 - REGISTER), you can choose this to login. You will be required to enter your username and
password.
2 - REGISTER - you will be asked your name, surname, a username you pick, and a password.
3 - SHOW FLIGHTS WITHIN 24 HOURS - shows all the flights whose departure date and time is within the next 24 hours.
4 - SHOW ALL AVAILABLE FLIGHTS - shows all the available flights in the database.
5 - SHOW THE DETAILED INFO ABOUT A FLIGHT - by using this you can view one additional field which is free seats of the flight.
6 - SEARCH FLIGHT - you will be asked your departure point ("From:"), where you are going to ("To:"),
when and the number of tickets you want to book. Then, the flights matching your input will be displayed.
7 - EXIT - you leave the application.
8 - HELP - choosing this, you can view this help text.


                                       USER MENU:
The first 4 menu items serves as the same purpose as in the main menu.
5 - BOOK - you will be asked the id of the flight you want to book a ticket of, the number of tickets you want to book,
and then the names and surnames of all the passengers.
6 - SEARCH AND BOOK - it firstly helps you to search for the flight suitable for you as SEARCH, and then asks
whether you want to book a flight or not, if "y" entered, 5 - BOOK is run, otherwise, you will be directed to user menu.
7 - CANCEL BOOKING - you will be asked to enter the index of the booking you have that you want to cancel
(You can view the indexes of your bookings choosing 8 - MY BOOKINGS).
9 - END SESSION - you log out of your account and directed to the main menu.
