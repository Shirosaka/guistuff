package dev.shirosaka.guistuff;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ignored) {
        }

        var dialogView = new DialogView();
        var dialogController = new DialogController(dialogView);
        dialogController.init();

        var view = new View();
        var controller = new Controller(view, dialogController);
        controller.init();
        view.setVisible(true);
    }
}
