package ua.goit.input;

import java.util.Scanner;

public class Console implements View {
    private Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
