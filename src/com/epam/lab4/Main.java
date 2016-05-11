package com.epam.lab4;

import com.epam.lab4.com.epam.lab4.model.Model;

public class Main {
    public static void main(String[] args) {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        // Run
        controller.processUser();
    }
}