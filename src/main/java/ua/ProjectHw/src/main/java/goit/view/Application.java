package goit.view;


import goit.controller.MainControler;

public class Application {
    public static void main(String[] args) {
        View view = new View();
        MainControler controler = new MainControler(view);

        controler.run();
    }
}

