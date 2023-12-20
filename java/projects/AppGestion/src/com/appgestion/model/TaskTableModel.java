package com.appgestion.model;


import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
    private List<Task> tasks;
    private String[] columnNames = {"Nom", "Description", "Date d'échéance", "Priorité", "Terminée"};

    public TaskTableModel(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setData(List<Task> tasks) {
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
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
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
    public Class<?> getColumnClass(int columnIndex) {
        // Retourne la classe de la valeur dans la colonne pour permettre un rendu correct
        if (columnIndex == 4) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Permet d'éditer la cellule si nécessaire
        return columnIndex == 4; // Par exemple, permettre l'édition de la colonne "Terminée"
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 4 && aValue instanceof Boolean) {
            tasks.get(rowIndex).setCompleted((Boolean) aValue);
            // Ajouter ici la logique pour mettre à jour la base de données si nécessaire
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
}