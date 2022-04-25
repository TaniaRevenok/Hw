package ua.goit.controller;

import ua.goit.pet.*;
import ua.goit.store.DeleteOrder;
import ua.goit.store.GetOrderById;
import ua.goit.store.PlaceOrder;
import ua.goit.store.ReturnInventory;
import ua.goit.user.*;
import ua.goit.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainControler {
    View view;
    private List<Command> commands;

    public MainControler(View view) {
        this.view = view;
        this.commands = new ArrayList<>(Arrays.asList(
                new Help(view),
                new CreateUser(),
                new CreateUserWithArray(view),
                new GetUserByUserName(view),
                new UpdateUser(view),
                new DeleteUser(view),
                new LogIn(view),
                new LogOut(),
                new GetPetById(view),
                new GetPetsByStatus(view),
                new DeletePet(view),
                new CreatePet(view),
                new UpdatePet(view),
                new UpdatePetById(view),
                new UploadPetImage(view),
                new PlaceOrder(),
                new ReturnInventory(),
                new GetOrderById(view),
                new DeleteOrder(view)
        ));
    }

    public void run() {
        view.write("Welcome to the application");
        doCommand();
    }

    private void doCommand() {
        boolean isNotExit = true;
        while (isNotExit) {
            view.write("Please enter a command or help to retrieve command list");
            String inputCommand = view.read();
            for (Command command : commands) {
                if (command.canProcess(inputCommand)) {
                    try {
                        command.process();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                } else if (inputCommand.equalsIgnoreCase("exit")) {
                    view.write("Good Bye!");
                    isNotExit = false;
                    break;
                }
            }
        }
    }
}

