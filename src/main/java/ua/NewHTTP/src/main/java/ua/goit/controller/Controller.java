package ua.goit.controller;

import ua.goit.command.Command;
import ua.goit.command.pet.*;
import ua.goit.command.store.DeleteOrder;
import ua.goit.command.store.GetOrderById;
import ua.goit.command.store.OrderPlace;
import ua.goit.command.store.ReturnInventory;
import ua.goit.command.user.*;
import ua.goit.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Controller {
    View view;
    private List<Command> commands;

    public Controller(View view) {
        this.view = view;
        this.commands = new ArrayList(Arrays.asList(
                new Help(view),
                new CreateUser(),
                new CreateUserWithArray(view),
                new GetUserByUsername(view),
                new UpdateUser(view),
                new DeleteUser(view),
                new LogIn(view),
                new LogOut(),
                new GetPetById(view),
                new GetPetByStatus(view),
                new DeletePet(view),
                new CreatePet(view),
                new UpdatePet(view),
                new UpdatePetById(view),
                new UploadPetImage(view),
                new OrderPlace(),
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
