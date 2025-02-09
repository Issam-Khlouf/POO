package view;

import javax.swing.*;
import java.awt.*;

public class TaskView extends JFrame {
    private JTextField taskField;
    private JButton addButton;
    private JButton removeButton;
    private JCheckBox completeCheckBox;
    private JTable taskTable;
    private JScrollPane scrollPane;

    public TaskView() {
        setTitle("To-Do List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskField = new JTextField();
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        completeCheckBox = new JCheckBox("Completed");
        taskTable = new JTable();
        scrollPane = new JScrollPane(taskTable);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(taskField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(completeCheckBox);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextField getTaskField() {
        return taskField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JCheckBox getCompleteCheckBox() {
        return completeCheckBox;
    }

    public JTable getTaskTable() {
        return taskTable;
    }
}