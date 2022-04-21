package ua.goit.model.util;

import ua.goit.model.entity.User;
import ua.goit.view.View;

import java.util.Arrays;
import java.util.List;

public class UserUtil {

    public User createUserThrowConsole() {
        View view = new View();
        User user = new User();
        view.write("Please write your username");
        user.setUserName(view.read());
        view.write("Please write your first name");
        user.setFirstName(view.read());
        view.write("Please write your lastname");
        user.setLastName(view.read());
        view.write("Please enter the email");
        user.setEmail(view.read());
        view.write("Please enter the password");
        user.setPassword(view.read());
        view.write("Please enter the phone number");
        user.setPassword(view.read());
        return user;
    }

    public List<User> createListOfUsers() {
        return Arrays.asList(
                new User("victoria", "Victoria", "Klymenko", "klymenko@gmail.com",
                        "654AVictoria", "0975362380"),
                new User("andrew", "Andrew", "White", "andrew1@gmail.com",
                        "654AVictoria", "0975362380"),
                new User("samuel", "Samuel", "Moon", "moon@gmail.com",
                        "654AVictoria", "0975362380"),
                new User("antony", "Antony", "Black", "vnkv@gmail.com",
                        "654AVictoria", "0975362380"),
                new User("sony", "Sofia", "White", "sofy@gmail.com",
                        "654AVictoria", "0975362380"),
                new User("vasya", "Vasyl", "Klymmenko", "vasia@gmail.com",
                        "654AVictoria", "0975362380")
        );



    }
}
