package controller;

import model.Task;
import model.TaskManager;
import view.TaskView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskController {
    private TaskView view;
    private TaskManager manager;

    public TaskController(TaskView view, TaskManager manager) {
        this.view = view;
        this.manager = manager;
        attachListeners();
        updateTable();
    }

    private void attachListeners() {
        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = view.getTaskField().getText();
                if (!description.isEmpty()) {
                    Task task = new Task(description);
                    manager.addTask(task);
                    view.getTaskField().setText("");
                    updateTable();
                }
            }
        });

        view.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTaskTable().getSelectedRow();
                if (selectedRow >= 0) {
                    manager.removeTask(selectedRow);
                    updateTable();
                }
            }
        });
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Description");
        model.addColumn("Status");

        for (Task task : manager.getTasks()) {
            model.addRow(new Object[]{task.getDescription(), task.isCompleted() ? "Completed" : "Pending"});
        }

        view.getTaskTable().setModel(model);
    }
}