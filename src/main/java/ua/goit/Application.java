package ua.goit;

import ua.goit.controller.MainControler;
import ua.goit.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new View();
        MainControler controler = new MainControler(view);

        controler.run();
    }
}
