package org.gentleninja.week3employeems.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gentleninja.week3employeems.model.Employee;
import org.gentleninja.week3employeems.service.EmployeeService;

public class EmployeeController {

    // --- Table ---
    @FXML private TableView<Employee<Integer>>      employeeTable;
    @FXML private TableColumn<Employee<Integer>, Integer> idCol;
    @FXML private TableColumn<Employee<Integer>, String>  nameCol, deptCol, activeCol;
    @FXML private TableColumn<Employee<Integer>, Double>  salaryCol, ratingCol;
    @FXML private TableColumn<Employee<Integer>, Integer> expCol;

    // --- Top bar ---
    @FXML private TextField searchField;

    // --- Sidebar ---
    @FXML private ComboBox<String> departmentFilter;
    @FXML private ComboBox<String> sortOptions;
    @FXML private TextField        minRatingField;
    @FXML private Label            totalEmployeesLabel;
    @FXML private Label            totalSalaryLabel;
    @FXML private Label            top5Label;

    // --- Bottom inputs ---
    @FXML private TextField idInput, nameInput, deptInput, salaryInput, ratingInput, expInput;
    @FXML private Label statusLabel;

    private final EmployeeService service = new EmployeeService();
    private ObservableList<Employee<Integer>> tableData;

    @FXML
    public void initialize() {
        // Bind columns
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("performanceRating"));
//        expCol.setCellValueFactory(new PropertyValueFactory<>("experience"));
//        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));

        // Populate sort & filter options
        departmentFilter.setItems(FXCollections.observableArrayList(
                "All", "HR", "IT", "Finance", "Marketing"));
        sortOptions.setItems(FXCollections.observableArrayList(
                "Name (A-Z)", "Salary (High-Low)", "Salary (Low-High)",
                "Performance (High-Low)", "Experience (High-Low)"));

        refreshTable(service.getAllEmployees());
    }

    // --- Search ---
    @FXML
    private void handleSearch() {
        String query = searchField.getText().trim();
        refreshTable(service.searchEmployeeByName(query));
        setStatus("Showing results for: " + query);
    }

    @FXML
    private void handleClear() {
        searchField.clear();
        refreshTable(service.getAllEmployees());
        setStatus("Cleared.");
    }

    // --- Filters ---
    @FXML
    private void handleDepartmentFilter() {
        String dept = departmentFilter.getValue();
        if (dept == null || dept.equals("All")) {
            refreshTable(service.getAllEmployees());
        } else {
            refreshTable(service.searchEmployeeByDepartment(dept));
        }
    }

    @FXML
    private void handleRatingFilter() {
        try {
            double minRating = Double.parseDouble(minRatingField.getText().trim());
            refreshTable(service.searchEmployeeByMinRating(minRating));
            setStatus("Filtered by min rating: " + minRating);
        } catch (NumberFormatException e) {
            setStatus("Invalid rating value.");
        }
    }

    // --- Sort ---
    @FXML
    private void handleSort() {
        String selected = sortOptions.getValue();
        if (selected == null) return;
        switch (selected) {
            case "Name"            -> refreshTable(service.sortByName());
            case "Salary"     -> refreshTable(service.sortBySalary());
            case "Performance)"-> refreshTable(service.sortByPerformance());
            case "Experience" -> refreshTable(service.sortByExperience());
        }
        setStatus("Sorted by: " + selected);
    }

    // --- Add ---
    @FXML
    private void handleAdd() {
        try {
            int    id     = Integer.parseInt(idInput.getText().trim());
            String name   = nameInput.getText().trim();
            String dept   = deptInput.getText().trim();
            double salary = Double.parseDouble(salaryInput.getText().trim());
            double rating = Double.parseDouble(ratingInput.getText().trim());
            int    exp    = Integer.parseInt(expInput.getText().trim());

            Employee<Integer> emp = new Employee<>(id, name, dept, salary, rating, exp, true);
            service.addEmployee(emp);
            refreshTable(service.getAllEmployees());
            clearInputs();
            setStatus("Employee added: " + name);
        } catch (NumberFormatException e) {
            setStatus("Please fill all fields correctly.");
        }
    }

    // --- Remove ---
    @FXML
    private void handleRemove() {
        Employee<Integer> selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.removeEmployee((Integer) selected.getId());
            refreshTable(service.getAllEmployees());
            setStatus("Removed: " + selected.getFullName());
        } else {
            setStatus("No employee selected.");
        }
    }

    // --- Helpers ---
    private void refreshTable(java.util.List<Employee<Integer>> list) {
        tableData = FXCollections.observableArrayList(list);
        employeeTable.setItems(tableData);
        updateStats();
    }

    private void updateStats() {
        totalEmployeesLabel.setText("Total: " + tableData.size());
        double sum = service.sumAllSalaries();
        totalSalaryLabel.setText(String.format("Salary Sum: %.0f", sum));
    }

    private void clearInputs() {
        idInput.clear(); nameInput.clear(); deptInput.clear();
        salaryInput.clear(); ratingInput.clear(); expInput.clear();
    }

    private void setStatus(String msg) {
        statusLabel.setText(msg);
    }
}