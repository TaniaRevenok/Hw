package goit.util;


import goit.entity.User;
import goit.view.View;

import java.util.Arrays;
import java.util.List;

public class UserUtil {

    public User createUserThrowConsole() {
        View view = new View();
        User user = new User();
        view.write("Please enter the username");
        user.setUserName(view.read());
        view.write("Please enter the first name");
        user.setFirstName(view.read());
        view.write("Please enter the last name");
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
                new User("vas", "Vasyl", "Petrov", "vas@test.com",
                        "Qwerty123", "0994534532"),
                new User("vano", "Ivan", "Petrov", "ip@test.com",
                        "Qwerty123", "0994534732"),
                new User("alex", "Alexandr", "Petrov", "alex@test.com",
                        "Qwerty123", "0994534532"),
                new User("vas", "Vasyl", "Petrov", "vas@test.com",
                        "Qwerty123", "0934534532"),
                new User("anatol", "Anatolii", "Petrov", "vtolyan@test.com",
                        "Qwerty123", "0994534032"),
                new User("kolyan", "Nikolay", "Petrov", "kolyan@test.com",
                        "Qwerty123", "0994634532")
        );
    }
}

