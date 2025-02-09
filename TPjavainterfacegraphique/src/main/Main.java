package main;

import controller.TaskController;
import model.TaskManager;
import view.TaskView;

public class Main {
    public static void main(String[] args) {
        TaskView view = new TaskView();
        TaskManager manager = new TaskManager();
        new TaskController(view, manager);

        view.setVisible(true);
    }
}