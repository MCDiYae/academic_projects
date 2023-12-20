package com.gestiontaches;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class TaskTableModel extends AbstractTableModel {

    private List<Task> tasks;
    private String[] columnNames = {"Nom", "Description", "Date d'échéance", "Priorité", "Complété"};

    public TaskTableModel(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return task.getName();
            case 1:
                return task.getDescription();
            case 2:
                return task.getDueDate();
            case 3:
                return task.getPriority();
            case 4:
                return task.isCompleted();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

	public void setData(List<Task> tasks2) {
		this.tasks = tasks;
		
	}
}
