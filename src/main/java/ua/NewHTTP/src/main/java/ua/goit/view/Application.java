package ua.goit.view;


import ua.goit.controller.Controller;

public class Application {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);

        controller.run();
    }
}
