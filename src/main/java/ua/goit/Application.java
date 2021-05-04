package ua.goit;

import ua.goit.input.Console;

public class Application {
    public static void main(String[] args) {
        Console console = new Console();
        Controler controler = new Controler(console);
        controler.run();
    }
}
