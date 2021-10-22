package morganVazana_RoeeElkoubi.view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import morganVazana_RoeeElkoubi.listeners.SystemUIEventsListener;
import morganVazana_RoeeElkoubi.model.Department;
import morganVazana_RoeeElkoubi.model.PreferenceType;
import morganVazana_RoeeElkoubi.model.Role;
import morganVazana_RoeeElkoubi.model.Employee.Employee;
 
public class View implements AbstractView {
	private ArrayList<SystemUIEventsListener> allListeners = new ArrayList<SystemUIEventsListener>();

	private Stage mainStage;
	private Scene mainScene;
	private GridPane gpNewCompany = new GridPane();
	private Label lbCompanyName = new Label("Enter Company Name: ");
	private TextField tfCompanyName = new TextField();
	private Button btnNewCompany = new Button("Submit");
	private BorderPane bpRoot = new BorderPane();

	private VBox vbLeft = new VBox();
	private Label lbMenuTitle = new Label("Company Efficiency Calculator System");
	private ToggleGroup tglMenuOption = new ToggleGroup();
	private RadioButton rdoMenuOption1 = new RadioButton("1) Create New Entity");
	private RadioButton rdoMenuOption2 = new RadioButton("2) Show Entity Data");
	private RadioButton rdoMenuOption3 = new RadioButton("3) Change Work Method");
	private RadioButton rdoMenuOption4 = new RadioButton("4) Show Company Profit");
	private HBox hbExit = new HBox();
	private Button btnExit = new Button("Save & Exit");

	private TabPane tabPane = new TabPane();
	private Tab tabNewDepartment = new Tab("Department");
	private Tab tabNewRole = new Tab("Role");
	private Tab tabNewEmployee = new Tab("Employee");

	private GridPane gpNewDepartment = new GridPane();
	private Label lbDepartmentName = new Label("Enter department name: ");
	private TextField tfDepartmentName = new TextField("Alphabetic characters only");
	private CheckBox cbDepartmentChangeable = new CheckBox("Work method changeable");
	private CheckBox cbDepartmentSyncable = new CheckBox("Syncronized department");
	private Button btnNewDepartment = new Button("Create Department");
	
	private GridPane gpNewRole = new GridPane();
	private Label lbRoleName = new Label("Enter role name: ");
	private TextField tfRoleName = new TextField("Alphabetic characters only");
	private Label lbRoleProfit = new Label("Enter profit per employee: ");
	private TextField tfRoleProfit = new TextField("Positive number only");
	private Label lbRoleDepartment = new Label("Select Department: ");
	private ComboBox<Department> cmbRoleDepartment = new ComboBox<Department>();
	private CheckBox cbRoleChangeable = new CheckBox("Work method changeable");
	private CheckBox cbRoleSyncable = new CheckBox("Syncronized role");
	private Button btnNewRole = new Button("Create Role");

	private GridPane gpNewEmployee = new GridPane();
	private Label lbEmployeeName = new Label("Enter employee name: ");
	private TextField tfEmployeeName = new TextField("Alphabetic characters only");
	private Label lbEmployeeID = new Label("Enter employee I.D: ");
	private TextField tfEmployeeID = new TextField("Must contain 9 digits");
	private Label lbSeletRole = new Label("Select Role: ");
	private ComboBox<Role> cmbEmployeeRole = new ComboBox<Role>();
	private Label lbEmployeeType = new Label("Select Type: ");
	private ComboBox<String> cmbEmployeeType = new ComboBox<String>();
	private Label lbEmployeeSalary = new Label("Enter Salary: ");
	private TextField tfEmployeeSalary = new TextField("Must be a positive number");
	private Label lbEmployeePrecentage = new Label("Enter Sales Percentage: ");
	private TextField tfEmployeePrecentage = new TextField("Integer or Decimal number");
	private Label lbSeletPreference = new Label("Select Preference: ");
	private ComboBox<String> cmbEmployeePreference = new ComboBox<String>();
	private Label lbSelectStartHour = new Label("Select Shift Start Hour: ");
	private ComboBox<String> cmbStartHour = new ComboBox<String>();
	private Button btnNewEmployee = new Button("Create Employee");
	
	private Tab tabShowDepartments = new Tab("Departments");
	private Tab tabShowRoles = new Tab("Roles");
	private Tab tabShowEmployees = new Tab("Employees");
	
	private VBox vbShowDepartments = new VBox();
	private HBox hbShowDepartments = new HBox();
	private Label lbShowDepartments = new Label("Select Department: ");
	private ComboBox<Department> cmbShowDepartments = new ComboBox<Department>();
	private HBox hbDepartmentText = new HBox();
	private Label lbDepartmentText = new Label();
	
	private VBox vbShowRoles = new VBox();
	private HBox hbShowRoles = new HBox();
	private Label lbShowRoles = new Label("Select Role: ");
	private ComboBox<Role> cmbShowRoles = new ComboBox<Role>();
	private HBox hbRoleText = new HBox();
	private Label lbRoleText = new Label();
	
	private VBox vbShowEmployees = new VBox();
	private HBox hbShowEmployees = new HBox();
	private Label lbShowEmployees = new Label("Select Employee: ");
	private ComboBox<Employee> cmbShowEmployees = new ComboBox<Employee>();
	private HBox hbEmployeeText = new HBox();
	private Label lbEmployeeText = new Label();
	
	private Tab tabChangeDepartmentPref = new Tab("Department");
	private Tab tabChangeRolePref = new Tab("Role");
	
	private GridPane gpDepartmentPref = new GridPane();
	private Label lbSelectDepartmentPref = new Label("Select Department: ");
	private ComboBox<Department> cmbSelectDepartmentPref = new ComboBox<Department>();
	private Label lbDepartmentPref = new Label("Select Department Preference: ");
	private ComboBox<String> cmbDepartmentPref = new ComboBox<String>();
	private Label lbDepartmentStartHour = new Label("Select Department Start Hour: ");
	private ComboBox<String> cmbDepartmentStartHour = new ComboBox<String>();
	private Button btnDepartmentPref = new Button("Submit Changes");
	
	private GridPane gpRolePref = new GridPane();
	private Label lbSeletRolePref = new Label("Select Role: ");
	private ComboBox<Role> cmbSelectRolePref = new ComboBox<Role>();
	private Label lbRolePref = new Label("Select Role Preference: ");
	private ComboBox<String> cmbRolePref = new ComboBox<String>();
	private Label lbRoleStartHour = new Label("Select Role Start Hour: ");
	private ComboBox<String> cmbRoleStartHour = new ComboBox<String>();
	private Button btnRolePref = new Button("Submit Changes");
	
	private Tab tabCompanyEfficiency = new Tab("Company");
	private Tab tabDepartmentsEfficiency = new Tab("Departments");
	private Tab tabEmployeesEfficiency = new Tab("Employees");
	
	private ScrollPane spCompanyEfficiency = new ScrollPane();
	private VBox vbCompanyEfficiency = new VBox();
	private Label lbCompanyEfficiencyName = new Label();
	private Label lbCompanyEfficiencyGreen = new Label();
	private Label lbCompanyEfficiencyRed = new Label();
	
	private VBox vbDepartmentEfficiency = new VBox();
	private HBox hbDepartmentEfficiency = new HBox();
	private Label lbDepartmentEfficiencyName = new Label("Select Department: ");
	private ComboBox<Department> cmbDepartmentEfficiency = new ComboBox<Department>();
	private Label lbDepartmentEfficiency = new Label();
	
	private VBox vbEmployeeEfficiency = new VBox();
	private HBox hbEmployeeEfficiency = new HBox();
	private Label lbEmployeeEfficiencyName = new Label("Select Employee: ");
	private ComboBox<Employee> cmbEmployeeEfficiency = new ComboBox<Employee>();
	private Label lbEmployeeEfficiency = new Label();

	private Alert informationMessage = new Alert(AlertType.INFORMATION);
	private Alert errorMessage = new Alert(AlertType.ERROR);

	public View(Stage primaryStage) {
		
		mainStage = primaryStage;
		mainStage.setTitle("V&E Company Efficiency Calculator 2021 \u00a9");
		mainStage.setResizable(false);
		
		// New Company
		gpNewCompany.setVgap(15);
		gpNewCompany.setHgap(15);
		gpNewCompany.add(lbCompanyName, 1, 1);
		gpNewCompany.add(tfCompanyName, 2, 1);
		tfCompanyName.setId("tf2");
		gpNewCompany.add(btnNewCompany, 2, 2);
		btnNewCompany.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (SystemUIEventsListener l : allListeners)
					l.createNewCompanyViewEvent(tfCompanyName.getText());
			}
		});

		// Left - Menu
		bpRoot.setLeft(vbLeft);
		vbLeft.setMinWidth(225);
		vbLeft.setSpacing(20);
		vbLeft.setPadding(new Insets(10));
		lbMenuTitle.setAlignment(Pos.CENTER);
		rdoMenuOption1.setToggleGroup(tglMenuOption);
		rdoMenuOption2.setToggleGroup(tglMenuOption);
		rdoMenuOption3.setToggleGroup(tglMenuOption);
		rdoMenuOption4.setToggleGroup(tglMenuOption);
		hbExit.getChildren().add(btnExit);
		hbExit.setAlignment(Pos.CENTER);
		vbLeft.getChildren().addAll(lbMenuTitle, rdoMenuOption1, rdoMenuOption2, rdoMenuOption3, rdoMenuOption4,
				hbExit);

		rdoMenuOption1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				tabPane.getTabs().clear();
				tabPane.getTabs().addAll(tabNewDepartment, tabNewRole, tabNewEmployee);
				tfDepartmentName.setText("Alphabetic characters only");
				tfDepartmentName.setId("tf1");
				tfRoleName.setText("Alphabetic characters only");
				tfRoleName.setId("tf1");
				tfRoleProfit.setText("Positive number only");
				tfRoleProfit.setId("tf1");
				tfEmployeeName.setText("Alphabetic characters only");
				tfEmployeeName.setId("tf1");
				tfEmployeeID.setText("Must contain 9 digits");
				tfEmployeeID.setId("tf1");
				tfEmployeeSalary.setText("Must be a positive number");
				tfEmployeeSalary.setId("tf1");
				tfEmployeePrecentage.setText("Integer or Decimal number");
				tfEmployeePrecentage.setId("tf1");
				cmbRoleDepartment.setValue(null);
				cmbEmployeeRole.setValue(null);
				cmbEmployeeType.setValue(null);
				cmbEmployeePreference.setValue(null);
			}
		});
		rdoMenuOption2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				tabPane.getTabs().clear();
				tabPane.getTabs().addAll(tabShowDepartments, tabShowRoles, tabShowEmployees);
				cmbShowDepartments.setValue(null);
				cmbShowRoles.setValue(null);
				cmbShowEmployees.setValue(null);
			}
		});
		rdoMenuOption3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				tabPane.getTabs().clear();
				tabPane.getTabs().addAll(tabChangeDepartmentPref, tabChangeRolePref);
				cmbSelectDepartmentPref.setValue(null);
				cmbDepartmentPref.setValue(null);
				cmbSelectRolePref.setValue(null);
				cmbRolePref.setValue(null);
			}
		});
		rdoMenuOption4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				tabPane.getTabs().clear();
				tabPane.getTabs().addAll(tabCompanyEfficiency, tabDepartmentsEfficiency, tabEmployeesEfficiency);
				cmbEmployeeEfficiency.setValue(null);
				cmbDepartmentEfficiency.setValue(null);
				for (SystemUIEventsListener l : allListeners)
					l.getCompanyEfficiencyViewEvent();
			}
		});
		btnExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (SystemUIEventsListener l : allListeners)
					l.terminateProgramViewEvent();
				mainStage.close();
			}
		});

		// Right

		// Create new entity
		tabNewDepartment.setContent(gpNewDepartment);
		tabNewRole.setContent(gpNewRole);
		tabNewEmployee.setContent(gpNewEmployee);
		gpNewDepartment.setVgap(15);
		gpNewDepartment.setHgap(15);
		gpNewDepartment.add(lbDepartmentName, 1, 1);
		gpNewDepartment.add(tfDepartmentName, 2, 1);
		tfDepartmentName.setId("tf1");
		tfDepartmentName.setPrefWidth(175);
		tfDepartmentName.setOnMouseClicked(e -> {
			tfDepartmentName.clear();
			tfDepartmentName.setId("tf2");
		});
		gpNewDepartment.add(cbDepartmentChangeable, 1, 2);
		gpNewDepartment.add(cbDepartmentSyncable, 2, 2);
		gpNewDepartment.add(btnNewDepartment, 2, 3);
		gpNewRole.setVgap(15);
		gpNewRole.setHgap(15);
		gpNewRole.add(lbRoleName, 1, 1);
		gpNewRole.add(tfRoleName, 2, 1);
		tfRoleName.setId("tf1");
		tfRoleName.setPrefWidth(175);
		tfRoleName.setOnMouseClicked(e -> {
			tfRoleName.clear();
			tfRoleName.setId("tf2");
		});
		gpNewRole.add(lbRoleProfit, 1, 2);
		gpNewRole.add(tfRoleProfit, 2, 2);
		tfRoleProfit.setId("tf1");
		tfRoleProfit.setPrefWidth(175);
		tfRoleProfit.setOnMouseClicked(e -> {
			tfRoleProfit.clear();
			tfRoleProfit.setId("tf2");
		});
		gpNewRole.add(lbRoleDepartment, 1, 3);
		gpNewRole.add(cmbRoleDepartment, 2, 3);
		gpNewRole.add(cbRoleChangeable, 1, 4);
		gpNewRole.add(cbRoleSyncable, 2, 4);
		gpNewRole.add(btnNewRole, 2, 5);
		cmbRoleDepartment.setMinWidth(175);
		cmbRoleDepartment.setMaxWidth(175);
		gpNewEmployee.setVgap(15);
		gpNewEmployee.setHgap(15);
		gpNewEmployee.add(lbEmployeeName, 1, 1);
		gpNewEmployee.add(tfEmployeeName, 2, 1);
		tfEmployeeName.setId("tf1");
		tfEmployeeName.setPrefWidth(175);
		tfEmployeeName.setOnMouseClicked(e -> {
			tfEmployeeName.clear();
			tfEmployeeName.setId("tf2");
		});
		gpNewEmployee.add(lbEmployeeID, 1, 2);
		gpNewEmployee.add(tfEmployeeID, 2, 2);
		tfEmployeeID.setId("tf1");
		tfEmployeeID.setPrefWidth(175);
		tfEmployeeID.setOnMouseClicked(e -> {
			tfEmployeeID.clear();
			tfEmployeeID.setId("tf2");
		});
		gpNewEmployee.add(lbSeletRole, 1, 3);
		gpNewEmployee.add(cmbEmployeeRole, 2, 3);
		cmbEmployeeRole.setMinWidth(175);
		cmbEmployeeRole.setMaxWidth(175);
		gpNewEmployee.add(lbEmployeeType, 1, 4);
		gpNewEmployee.add(cmbEmployeeType, 2, 4);

		gpNewEmployee.add(lbEmployeePrecentage, 3, 4);
		lbEmployeePrecentage.setVisible(false);
		gpNewEmployee.add(lbEmployeeSalary, 1, 5);
		gpNewEmployee.add(tfEmployeeSalary, 2, 5);
		tfEmployeeSalary.setId("tf1");
		tfEmployeeSalary.setPrefWidth(175);
		tfEmployeeSalary.setOnMouseClicked(e -> {
			tfEmployeeSalary.clear();
			tfEmployeeSalary.setId("tf2");
		});
		gpNewEmployee.add(tfEmployeePrecentage, 3, 5);
		tfEmployeePrecentage.setId("tf1");
		tfEmployeePrecentage.setPrefWidth(175);
		tfEmployeePrecentage.setOnMouseClicked(e -> {
			tfEmployeePrecentage.clear();
			tfEmployeePrecentage.setId("tf2");
		});
		tfEmployeePrecentage.setVisible(false);
		gpNewEmployee.add(lbSeletPreference, 1, 6);
		gpNewEmployee.add(cmbEmployeePreference, 2, 6);
		cmbEmployeePreference.setMinWidth(175);
		cmbEmployeePreference.setMaxWidth(175);
		lbSelectStartHour.setVisible(false);
		cmbStartHour.setVisible(false);
		gpNewEmployee.add(lbSelectStartHour, 1, 7);
		gpNewEmployee.add(cmbStartHour, 2, 7);
		gpNewEmployee.add(btnNewEmployee, 2, 8);
		cmbEmployeeType.setMinWidth(175);
		cmbEmployeeType.setMaxWidth(175);
		cmbEmployeeType.getItems().addAll("Global Salary", "Per Hour", "Global + Bonus Salary");
		cmbEmployeeType.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbEmployeeType.getValue() != null) {
					switch (cmbEmployeeType.getValue()) {
					case "Global + Bonus Salary":
						lbEmployeePrecentage.setVisible(true);
						tfEmployeePrecentage.setVisible(true);
						tfEmployeePrecentage.setText("Integer or Decimal number ");
						break;
					default:
						lbEmployeePrecentage.setVisible(false);
						tfEmployeePrecentage.setVisible(false);
						break;
					}
				} else {
					lbEmployeePrecentage.setVisible(false);
					tfEmployeePrecentage.setVisible(false);
				}
			}
		});
		cmbEmployeePreference.setMinWidth(175);
		cmbEmployeePreference.setMaxWidth(175);
		cmbEmployeePreference.getItems().addAll("Start Early", "Start Late", "No Change", "Work From Home");
		cmbEmployeePreference.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbEmployeePreference.getValue() == null) { 
					lbSelectStartHour.setVisible(false);
					cmbStartHour.setVisible(false);
				} else if (cmbEmployeePreference.getValue().equals("Start Early")) {
					setCmbStartHourEarly(cmbStartHour);
					lbSelectStartHour.setText("Select Shift Start Hour: ");
					lbSelectStartHour.setVisible(true);
					cmbStartHour.setVisible(true);
				} else if (cmbEmployeePreference.getValue().equals("Start Late")) {
					setCmbStartHourLate(cmbStartHour);
					lbSelectStartHour.setText("Select Shift Start Hour: ");
					lbSelectStartHour.setVisible(true);
					cmbStartHour.setVisible(true);
				} else if (cmbEmployeePreference.getValue().equals("No Change")) {
					lbSelectStartHour.setText("Shift start hour is: 08:00 ");
					lbSelectStartHour.setVisible(true);
					cmbStartHour.setVisible(false);
				} else if (cmbEmployeePreference.getValue().equals("Work From Home")) {
					lbSelectStartHour.setText("Working from home will be\navailable only if allowed\nby the depratment\\role.");
					lbSelectStartHour.setVisible(true);
					cmbStartHour.setVisible(false);
				} 
			}
		});
		// New entities buttons action
		btnNewDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (SystemUIEventsListener l : allListeners)
					l.createDepartmentViewEvent(tfDepartmentName.getText(), cbDepartmentChangeable.isSelected(),
							cbDepartmentSyncable.isSelected());
				tfDepartmentName.setText("Alphabetic characters only");
				tfDepartmentName.setId("tf1");
				cbDepartmentChangeable.setSelected(false);
				cbDepartmentSyncable.setSelected(false);
			}
		});
		btnNewRole.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (SystemUIEventsListener l : allListeners)
					l.createRoleViewEvent(tfRoleName.getText(), tfRoleProfit.getText(), cmbRoleDepartment.getValue(),
							cbRoleChangeable.isSelected(), cbRoleSyncable.isSelected());
				tfRoleName.setText("Alphabetic characters only");
				tfRoleName.setId("tf1");
				tfRoleProfit.setText("Positive number only");
				tfRoleProfit.setId("tf1");
				cmbRoleDepartment.setValue(null);
				cbRoleChangeable.setSelected(false);
				cbRoleSyncable.setSelected(false);
			}
		});
		
		btnNewEmployee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbEmployeeType.getValue() == null)
					exceptionMessage("You must choose a type.");
				else {
					int type = -1;
					switch (cmbEmployeeType.getValue()) {
					case "Global Salary":
						type = 1;
						break;
					case "Per Hour":
						type = 2;
						break;
					case "Global + Bonus Salary":
						type = 3;
						break;
					}
					for (SystemUIEventsListener l : allListeners) {
						if (cmbEmployeePreference.getValue() == null)
							exceptionMessage("You must choose a preference.");
						else {
							switch (cmbEmployeePreference.getValue()) {
							case "Start Early":
								l.createEmployeeViewEvent(type, tfEmployeeName.getText(), tfEmployeeID.getText(),
										PreferenceType.START_EARLY, cmbStartHour.getValue(), cmbEmployeeRole.getValue(),
										tfEmployeeSalary.getText(), tfEmployeePrecentage.getText());
								break;
							case "Start Late":
								l.createEmployeeViewEvent(type, tfEmployeeName.getText(), tfEmployeeID.getText(),
										PreferenceType.START_LATE, cmbStartHour.getValue(), cmbEmployeeRole.getValue(),
										tfEmployeeSalary.getText(), tfEmployeePrecentage.getText());
								break;
							case "No Change":
								l.createEmployeeViewEvent(type, tfEmployeeName.getText(), tfEmployeeID.getText(),
										PreferenceType.NO_CHANGE, cmbStartHour.getValue(), cmbEmployeeRole.getValue(),
										tfEmployeeSalary.getText(), tfEmployeePrecentage.getText());
								break;
							case "Work From Home":
								l.createEmployeeViewEvent(type, tfEmployeeName.getText(), tfEmployeeID.getText(),
										PreferenceType.WORK_FROM_HOME, cmbStartHour.getValue(),
										cmbEmployeeRole.getValue(), tfEmployeeSalary.getText(),
										tfEmployeePrecentage.getText());
								break;
							} 
						}
					}
				}
				tfEmployeeName.setText("Alphabetic characters only");
				tfEmployeeName.setId("tf1");
				tfEmployeeID.setText("Must contain 9 digits");
				tfEmployeeID.setId("tf1");
				cmbEmployeeRole.setValue(null);
				cmbEmployeeType.setValue(null);
				tfEmployeeSalary.setText("Must be a positive number");
				tfEmployeeSalary.setId("tf1");
				tfEmployeePrecentage.setText("Integer or Decimal number");
				tfEmployeePrecentage.setId("tf1");
				cmbEmployeePreference.setValue(null);
				lbSelectStartHour.setVisible(false);
				cmbStartHour.setVisible(false);
			}
		});
		
		//Show Entities
		tabShowDepartments.setContent(vbShowDepartments);
		tabShowRoles.setContent(vbShowRoles);
		tabShowEmployees.setContent(vbShowEmployees);
		vbShowDepartments.setSpacing(10);
		vbShowDepartments.setAlignment(Pos.TOP_CENTER);
		vbShowDepartments.setPadding(new Insets(10));
		hbShowDepartments.setSpacing(10);
		hbShowDepartments.setPadding(new Insets(10));
		cmbShowDepartments.setMinWidth(220);
		cmbShowDepartments.setMaxWidth(220);
		hbShowDepartments.getChildren().addAll(lbShowDepartments, cmbShowDepartments);
		hbDepartmentText.setSpacing(10);
		hbDepartmentText.setPadding(new Insets(10));
		hbDepartmentText.getChildren().add(lbDepartmentText);
		vbShowDepartments.getChildren().addAll(hbShowDepartments, hbDepartmentText);
		vbShowRoles.setSpacing(10);
		vbShowRoles.setAlignment(Pos.TOP_CENTER);
		vbShowRoles.setPadding(new Insets(10));
		hbShowRoles.setSpacing(10);
		hbShowRoles.setPadding(new Insets(10));
		cmbShowRoles.setMinWidth(220);
		cmbShowRoles.setMaxWidth(220);
		hbShowRoles.getChildren().addAll(lbShowRoles, cmbShowRoles);
		hbRoleText.setSpacing(10);
		hbRoleText.setPadding(new Insets(10));
		hbRoleText.getChildren().add(lbRoleText);
		vbShowRoles.getChildren().addAll(hbShowRoles, hbRoleText);
		vbShowEmployees.setSpacing(10);
		vbShowEmployees.setAlignment(Pos.TOP_CENTER);
		vbShowEmployees.setPadding(new Insets(10));
		hbShowEmployees.setSpacing(10);
		hbShowEmployees.setPadding(new Insets(10));
		cmbShowEmployees.setMinWidth(220);
		cmbShowEmployees.setMaxWidth(220);
		hbShowEmployees.getChildren().addAll(lbShowEmployees, cmbShowEmployees);
		hbEmployeeText.setSpacing(10);
		hbEmployeeText.setPadding(new Insets(10));
		hbEmployeeText.getChildren().add(lbEmployeeText);
		vbShowEmployees.getChildren().addAll(hbShowEmployees, hbEmployeeText);
		cmbShowDepartments.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbShowDepartments.getValue() == null)
					lbDepartmentText.setText(null);
				else
					lbDepartmentText.setText(cmbShowDepartments.getValue().getDepartmentInformation());
			}
		});
		cmbShowRoles.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbShowRoles.getValue() == null)
					lbRoleText.setText(null);
				else
					lbRoleText.setText(cmbShowRoles.getValue().getRoleInformation());
			}
		});
		cmbShowEmployees.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbShowEmployees.getValue() == null)
					lbEmployeeText.setText(null);
				else
					lbEmployeeText.setText(cmbShowEmployees.getValue().getEmployeeInformation());
			}
		});
		
		//Change work method
		tabChangeDepartmentPref.setContent(gpDepartmentPref);
		tabChangeRolePref.setContent(gpRolePref);
		gpDepartmentPref.setVgap(15);
		gpDepartmentPref.setHgap(15);
		gpDepartmentPref.add(lbSelectDepartmentPref, 1, 1);
		gpDepartmentPref.add(cmbSelectDepartmentPref, 2, 1);
		cmbSelectDepartmentPref.setMinWidth(220);
		cmbSelectDepartmentPref.setMaxWidth(220);
		gpDepartmentPref.add(lbDepartmentPref, 1, 2);
		gpDepartmentPref.add(cmbDepartmentPref, 2, 2);
		gpDepartmentPref.add(lbDepartmentStartHour, 1, 3);
		gpDepartmentPref.add(cmbDepartmentStartHour, 2, 3);
		gpDepartmentPref.add(btnDepartmentPref, 2, 4);
		lbDepartmentStartHour.setVisible(false);
		cmbDepartmentStartHour.setVisible(false);
		cmbDepartmentPref.setMinWidth(220);
		cmbDepartmentPref.setMaxWidth(220);
		cmbDepartmentPref.getItems().addAll("Start Early", "Start Late", "No Change", "Work From Home");
		cmbDepartmentPref.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbDepartmentPref.getValue() == null) {
					lbDepartmentStartHour.setVisible(false);
					cmbDepartmentStartHour.setVisible(false);
				} else if (cmbDepartmentPref.getValue().equals("Start Early")) {
					setCmbStartHourEarly(cmbDepartmentStartHour);
					lbDepartmentStartHour.setText("Select Department Shift Start Hour: ");
					lbDepartmentStartHour.setVisible(true);
					cmbDepartmentStartHour.setVisible(true);
				} else if (cmbDepartmentPref.getValue().equals("Start Late")) {
					setCmbStartHourLate(cmbDepartmentStartHour);
					lbDepartmentStartHour.setText("Select Department Shift Start Hour: ");
					lbDepartmentStartHour.setVisible(true);
					cmbDepartmentStartHour.setVisible(true);
				} else if (cmbDepartmentPref.getValue().equals("No Change")) {
					lbDepartmentStartHour.setText("Department shift start hour is: 08:00 ");
					lbDepartmentStartHour.setVisible(true);
					cmbDepartmentStartHour.setVisible(false);
				} else if (cmbDepartmentPref.getValue().equals("Work From Home")) {
					lbDepartmentStartHour.setText("Working from home\nhas flexible shift hours."); // CHANGE TEXT!!
					lbDepartmentStartHour.setVisible(true);
					cmbDepartmentStartHour.setVisible(false);
				}
			}
		});
		cmbDepartmentStartHour.setMinWidth(220);
		cmbDepartmentStartHour.setMaxWidth(220);
		btnDepartmentPref.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (SystemUIEventsListener l : allListeners) {
					if (cmbDepartmentPref.getValue() == null)
						exceptionMessage("You must select a department preference");
					else {
						switch (cmbDepartmentPref.getValue()) {
						case "Start Early":
							l.changeDepartmentPref(cmbSelectDepartmentPref.getValue(), PreferenceType.START_EARLY,
									cmbDepartmentStartHour.getValue());
							break;
						case "Start Late":
							l.changeDepartmentPref(cmbSelectDepartmentPref.getValue(), PreferenceType.START_LATE,
									cmbDepartmentStartHour.getValue());
							break;
						case "No Change":
							l.changeDepartmentPref(cmbSelectDepartmentPref.getValue(), PreferenceType.NO_CHANGE,
									"08:00");
							break;
						case "Work From Home":
							l.changeDepartmentPref(cmbSelectDepartmentPref.getValue(), PreferenceType.WORK_FROM_HOME,
									"08:00");
							break;
						}
					}
				}
			}
		});
		gpRolePref.setVgap(15);
		gpRolePref.setHgap(15);
		gpRolePref.add(lbSeletRolePref, 1, 1);
		gpRolePref.add(cmbSelectRolePref, 2, 1);
		cmbSelectRolePref.setMinWidth(220);
		cmbSelectRolePref.setMaxWidth(220);
		gpRolePref.add(lbRolePref, 1, 2);
		gpRolePref.add(cmbRolePref, 2, 2);
		gpRolePref.add(lbRoleStartHour, 1, 3);
		gpRolePref.add(cmbRoleStartHour, 2, 3);
		gpRolePref.add(btnRolePref, 2, 4);
		lbRoleStartHour.setVisible(false);
		cmbRoleStartHour.setVisible(false);
		cmbRolePref.setMinWidth(220);
		cmbRolePref.setMaxWidth(220);
		cmbRolePref.getItems().addAll("Start Early", "Start Late", "No Change", "Work From Home");
		cmbRolePref.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbRolePref.getValue() == null) {
					lbRoleStartHour.setVisible(false);
					cmbRoleStartHour.setVisible(false);
				} else if (cmbRolePref.getValue().equals("Start Early")) {
					setCmbStartHourEarly(cmbRoleStartHour);
					lbRoleStartHour.setText("Select Role Shift Start Hour: ");
					lbRoleStartHour.setVisible(true);
					cmbRoleStartHour.setVisible(true);
				} else if (cmbRolePref.getValue().equals("Start Late")) {
					setCmbStartHourLate(cmbRoleStartHour);
					lbRoleStartHour.setText("Select Role Shift Start Hour: ");
					lbRoleStartHour.setVisible(true);
					cmbRoleStartHour.setVisible(true);
				} else if (cmbRolePref.getValue().equals("No Change")) {
					lbRoleStartHour.setText("Role shift start hour is: 08:00 ");
					lbRoleStartHour.setVisible(true);
					cmbRoleStartHour.setVisible(false);
				} else if (cmbRolePref.getValue().equals("Work From Home")) {
					lbRoleStartHour.setText("Working from home\nhas flexible shift hours.");
					lbRoleStartHour.setVisible(true);
					cmbRoleStartHour.setVisible(false);
				}
			}
		});
		cmbRoleStartHour.setMinWidth(220);
		cmbRoleStartHour.setMaxWidth(220);
		btnRolePref.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (SystemUIEventsListener l : allListeners) {
					if (cmbRolePref.getValue() == null)
						exceptionMessage("You must select a role preference");
					else {
						switch (cmbRolePref.getValue()) {
						case "Start Early":
							l.changeRolePref(cmbSelectRolePref.getValue(), PreferenceType.START_EARLY,
									cmbRoleStartHour.getValue());
							break;
						case "Start Late":
							l.changeRolePref(cmbSelectRolePref.getValue(), PreferenceType.START_LATE,
									cmbRoleStartHour.getValue());
							break;
						case "No Change":
							l.changeRolePref(cmbSelectRolePref.getValue(), PreferenceType.NO_CHANGE, "08:00");
							break;
						case "Work From Home":
							l.changeRolePref(cmbSelectRolePref.getValue(), PreferenceType.WORK_FROM_HOME, "08:00");
							break;
						}
					}
				}
			}
		});
		
		//Show Calculated Efficiency
		tabCompanyEfficiency.setContent(spCompanyEfficiency);
		tabDepartmentsEfficiency.setContent(vbDepartmentEfficiency);
		tabEmployeesEfficiency.setContent(vbEmployeeEfficiency);
		spCompanyEfficiency.setContent(vbCompanyEfficiency);
		vbCompanyEfficiency.setSpacing(15);
		vbCompanyEfficiency.setPadding(new Insets(10));
		lbCompanyEfficiencyName.setAlignment(Pos.TOP_CENTER);
		vbCompanyEfficiency.getChildren().addAll(lbCompanyEfficiencyName, lbCompanyEfficiencyGreen, lbCompanyEfficiencyRed);
		lbCompanyEfficiencyGreen.setId("profitGreen");
		lbCompanyEfficiencyRed.setId("profitRed");
		vbDepartmentEfficiency.setSpacing(15);
		vbDepartmentEfficiency.setPadding(new Insets(10));
		hbDepartmentEfficiency.setAlignment(Pos.CENTER);
		hbDepartmentEfficiency.setSpacing(10);
		hbDepartmentEfficiency.setPadding(new Insets(10));
		cmbDepartmentEfficiency.setMinWidth(220);
		cmbDepartmentEfficiency.setMaxWidth(220);
		hbDepartmentEfficiency.getChildren().addAll(lbDepartmentEfficiencyName, cmbDepartmentEfficiency);
		vbDepartmentEfficiency.getChildren().addAll(hbDepartmentEfficiency, lbDepartmentEfficiency);
		cmbDepartmentEfficiency.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbDepartmentEfficiency.getValue() == null)
					lbDepartmentEfficiency.setText(null);
				else
					lbDepartmentEfficiency.setText(cmbDepartmentEfficiency.getValue().getDepartmentEfficiency());
			}
		});
		vbEmployeeEfficiency.setSpacing(15);
		vbEmployeeEfficiency.setPadding(new Insets(10));
		hbEmployeeEfficiency.setAlignment(Pos.CENTER);
		hbEmployeeEfficiency.setSpacing(10);
		hbEmployeeEfficiency.setPadding(new Insets(10));
		cmbEmployeeEfficiency.setMinWidth(220);
		cmbEmployeeEfficiency.setMaxWidth(220);
		hbEmployeeEfficiency.getChildren().addAll(lbEmployeeEfficiencyName, cmbEmployeeEfficiency);
		vbEmployeeEfficiency.getChildren().addAll(hbEmployeeEfficiency, lbEmployeeEfficiency);
		cmbEmployeeEfficiency.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbEmployeeEfficiency.getValue() == null)
					lbEmployeeEfficiency.setText(null);
				else
					lbEmployeeEfficiency.setText(cmbEmployeeEfficiency.getValue().getEfficiency());
			}
		});
		
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		bpRoot.setCenter(tabPane);
	}

	private void setCmbStartHourEarly(ComboBox<String> cmb) {
		cmb.getItems().clear();
		cmb.getItems().add("00:00");
		cmb.getItems().add("01:00");
		cmb.getItems().add("02:00");
		cmb.getItems().add("03:00");
		cmb.getItems().add("04:00");
		cmb.getItems().add("05:00");
		cmb.getItems().add("06:00");
		cmb.getItems().add("07:00");
	}
	
	private void setCmbStartHourLate(ComboBox<String> cmb) {
		cmb.getItems().clear();
		cmb.getItems().add("09:00");
		cmb.getItems().add("10:00");
		cmb.getItems().add("11:00");
		cmb.getItems().add("12:00");
		cmb.getItems().add("13:00");
		cmb.getItems().add("14:00");
		cmb.getItems().add("15:00");
		cmb.getItems().add("16:00");
		cmb.getItems().add("17:00");
		cmb.getItems().add("18:00");
		cmb.getItems().add("19:00");
		cmb.getItems().add("20:00");
		cmb.getItems().add("21:00");
		cmb.getItems().add("22:00");
		cmb.getItems().add("23:00");
	}
	
	@Override
	public void getFileCheckAnswer(boolean answer) {
		if (answer)
			mainScene = new Scene(bpRoot, 800, 450);
		else
			mainScene = new Scene(gpNewCompany, 400, 150);
		
		mainScene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
		mainStage.setScene(mainScene);
		mainStage.show();
	}
	
	@Override
	public void createNewCompanyModelEvent() {
		mainScene = new Scene(bpRoot, 800, 450);
		mainScene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
		mainStage.setScene(mainScene);
	}

	@Override
	public void registerListener(SystemUIEventsListener l) {
		allListeners.add(l);
	}

	@Override
	public void successfulMessage(String message) {
		informationMessage.setTitle("Information Dialog");
		informationMessage.setHeaderText(null);
		informationMessage.setContentText(message);
		informationMessage.showAndWait();
	}

	@Override
	public void exceptionMessage(String message) {
		errorMessage.setTitle("Exception Dialog");
		errorMessage.setHeaderText("Exception occured");
		errorMessage.setContentText(message);
		errorMessage.showAndWait();
	}

	@Override
	public void createDepartmentModelEvent(Department department) {
		cmbRoleDepartment.getItems().add(department);
		cmbShowDepartments.getItems().add(department);
		cmbSelectDepartmentPref.getItems().add(department);
		cmbDepartmentEfficiency.getItems().add(department);
	}

	@Override
	public void createRoleModelEvent(Role role) {
		cmbEmployeeRole.getItems().add(role);
		cmbShowRoles.getItems().add(role);
		cmbSelectRolePref.getItems().add(role);
	}

	@Override
	public void createEmployeeModelEvent(Employee employee) {
		cmbShowEmployees.getItems().add(employee);
		cmbEmployeeEfficiency.getItems().add(employee);
	}

	@Override
	public void getCompanyEfficiencyModelEvent(String information, String profit, double companyProfit) {
		lbCompanyEfficiencyName.setText(information);
		lbCompanyEfficiencyGreen.setText(profit);
		lbCompanyEfficiencyRed.setText(profit);
		if (companyProfit > 0) {
			vbCompanyEfficiency.getChildren().clear();
			vbCompanyEfficiency.getChildren().addAll(lbCompanyEfficiencyName, lbCompanyEfficiencyGreen);
		} else {
			vbCompanyEfficiency.getChildren().clear();
			vbCompanyEfficiency.getChildren().addAll(lbCompanyEfficiencyName, lbCompanyEfficiencyRed);
		}
	}
	
}
