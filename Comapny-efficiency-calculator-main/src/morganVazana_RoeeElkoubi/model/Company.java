package morganVazana_RoeeElkoubi.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import morganVazana_RoeeElkoubi.listeners.SystemEventsListener;
import morganVazana_RoeeElkoubi.model.Employee.Employee;
import morganVazana_RoeeElkoubi.model.Employee.GlobalSalaryEmployee;
import morganVazana_RoeeElkoubi.model.Employee.HourlyEmployee;
import morganVazana_RoeeElkoubi.model.Employee.SalesBonusEmployee;

public class Company implements ICompany, Serializable {
	private String name;
	private ArrayList<Department> departmentsList;
	private transient ArrayList<SystemEventsListener> allListeners;
	private boolean hasStartedExperiment;
	private DecimalFormat df = new DecimalFormat("#0.0");
	
	public Company () throws Exception {
		this.departmentsList = new ArrayList<Department>();
		this.allListeners = new ArrayList<SystemEventsListener>();
		this.hasStartedExperiment = false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
		if (name == null || name.isEmpty())
			throw new Exception("You must enter a company name.");
		this.name = name;
	}

	public ArrayList<Department> getDepartmentsList() {
		return departmentsList;
	}

	public void setDepartmentsList(ArrayList<Department> departmentsList) {
		this.departmentsList = departmentsList;
	}
	
	public boolean hasStartedExperiment() {
		return hasStartedExperiment;
	}

	public void setHasStartedExperiment(boolean hasStartedExperiment) {
		this.hasStartedExperiment = hasStartedExperiment;
	}

	@Override
	public void createNewCompany(String name) throws Exception {
		setName(name);
		addHardCodedObjects();
		fireCreatedNewCompany();
	}

	@Override
	public void addHardCodedObjects() throws Exception {
		//Departments
		Department d1 = new Department("Management", false, false);
		Department d2 = new Department("Research and Development", false, true);
		Department d3 = new Department("Human Resource", true, false);
		Department d4 = new Department("Customer Service", true, true);
		departmentsList.add(d1);
		departmentsList.add(d2);
		departmentsList.add(d3);
		departmentsList.add(d4);
		fireCreateDepartmentModelEvent(d1);
		fireCreateDepartmentModelEvent(d2);
		fireCreateDepartmentModelEvent(d3);
		fireCreateDepartmentModelEvent(d4);
		
		//Roles
		Role r1 = new Role("CEO", 100, d1, false, true);
		Role r2 = new Role("Research and Development Manager", 50, d1, false, false);
		Role r3 = new Role("Head Developer", 30, d2, true, false);
		Role r4 = new Role("Talent Aquisition", 10, d3, true, true);
		Role r5 = new Role("Technical Support Specialist", 8, d4, true, true);
		r1.getDepartment().addNewRole(r1);
		r2.getDepartment().addNewRole(r2);
		r3.getDepartment().addNewRole(r3);
		r4.getDepartment().addNewRole(r4);
		r5.getDepartment().addNewRole(r5);
		fireCreateRoleModelEvent(r1);
		fireCreateRoleModelEvent(r2);
		fireCreateRoleModelEvent(r3);
		fireCreateRoleModelEvent(r4);
		fireCreateRoleModelEvent(r5);
		
		//Employees
		createEmployee(1, "Jeff", "111111111", PreferenceType.NO_CHANGE, "08:00", r1, "10000000", "0");
		createEmployee(1, "Moshe", "111111112", PreferenceType.START_LATE, "11:00", r2, "1000000", "0");
		createEmployee(3, "Tom", "111111113", PreferenceType.WORK_FROM_HOME, "08:00", r3, "500000", "5.5");
		createEmployee(2, "Jenny", "111111114", PreferenceType.START_EARLY, "06:00", r4, "100", "0");
		createEmployee(1, "Kenny", "111111115", PreferenceType.NO_CHANGE, "08:00", r5, "50000", "0");
	}

	@Override
	public void registerListener(SystemEventsListener l) {
		allListeners.add(l);
	}
	
	@Override
	public void terminateProgram() throws Exception {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Company.dat"));
		outFile.writeObject(this);
		outFile.close();
	}
	
	@Override
	public void checkReadFromFile() throws Exception {
		try {
			ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("Company.dat"));
			Company c = (Company) inFile.readObject();
			setName(c.getName());
			setDepartmentsList(c.getDepartmentsList());
			setHasStartedExperiment(c.hasStartedExperiment);
			inFile.close();
			for (SystemEventsListener l : allListeners)
				l.getFileCheckAnswer(true);
			int employeesCounter = 0;
			for (Department d : departmentsList) {
				fireCreateDepartmentModelEvent(d);
				for (Role r : d.getRolesList()) {
					fireCreateRoleModelEvent(r);
					for (Employee e : r.getEmployeesList()) {
						fireCreateEmployeeModelEvent(e);
						employeesCounter++;
					}
				}
			}
			Employee.setEmployeeIDGenerator(++employeesCounter);
		} catch (FileNotFoundException e) {
			for (SystemEventsListener l : allListeners)
				l.getFileCheckAnswer(false);
			throw new Exception("Saved file does not exists.\nCreating a new file");
		}
	}

	private void checkValidNameInput(String name) throws Exception {
		for (int i = 0 ; i < name.length() ; i++) {
			if (!(Character.isLetter(name.charAt(i)) || name.charAt(i) == ' '))
				throw new Exception("The name must contain alphabetic characters only.");
		}
		if (name.isEmpty() || name.equals("Alphabetic characters only")) 
			throw new Exception("You must enter a name.");
	}
	
	private int checkValidNumberInput(String str) throws Exception {
		if (str == null || str.isEmpty())
			throw new Exception("You must enter a positive number.");
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9')
				throw new Exception("You must enter a number.");
		}
		return Integer.parseInt(str);
	}
	
	private double checkValidDoubleInput(String str) throws Exception {
		if (str == null || str.isEmpty())
			throw new Exception("You must enter an employees sales percentage.");
		for (int i = 0; i < str.length(); i++) {
			if (!(str.charAt(i) > '0' && str.charAt(i) < '9' || str.charAt(i) == '.'))
				throw new Exception("Sales percentage must contain digits or a dot only.\n(12.3, 4.5, 7.85 etc.)");
		}
		return Double.parseDouble(str);
	}

	@Override
	public void createDepartment(String name, boolean canChangeWorkHours,
			boolean syncedDepartment) throws Exception {
		checkValidNameInput(name);
			
		Department d = new Department(name, canChangeWorkHours, syncedDepartment);
		for (Department d1 : departmentsList)
			if (d1.equals(d))
				throw new Exception("This department already exists.");
		
		departmentsList.add(d);
		fireCreateDepartmentModelEvent(d);
	}
	
	@Override
	public void createRole(String name, String strProfitPerEmployee, Department department, boolean canChangeWorkMethod,
			boolean syncronizedRole) throws Exception {
		checkValidNameInput(name);
		int profitPerEmployee = checkValidNumberInput(strProfitPerEmployee);
		if (department == null) 
			throw new Exception("You must choose a department.");
		
		Role r = new Role(name, profitPerEmployee, department, canChangeWorkMethod, syncronizedRole);
		
		department.addNewRole(r);
		fireCreateRoleModelEvent(r);
	}

	@Override
	public void createEmployee(int type, String name, String id, PreferenceType pType, String strPreferedStartHour, Role role, String strSalary,
			 String strSalesPercentage) throws Exception {
		if (type == -1)
			throw new Exception("You must choose a type");
		checkValidNameInput(name);
		if (id.length() != 9)
			throw new Exception("An I.D. number must contain 9 digits.");
		for (int i=0 ; i < id.length() ; i++) {
			if (id.charAt(i) < '0' || id.charAt(i) > '9' ) 
				throw new Exception("An I.D. must contain numbers only.");
		}
		if (pType == null)
			throw new Exception("You must choose a preference type.");
		if (role == null)
			throw new Exception("You must choose a role.");
		int preferedStartHour = 0;
		if (pType == PreferenceType.START_EARLY || pType == PreferenceType.START_LATE) {
			if (strPreferedStartHour == null) {
				throw new Exception("You must choose a prefered start hour.");			
			}
			String[] hourSplit = strPreferedStartHour.split(":", 2);
			preferedStartHour = Integer.parseInt(hourSplit[0]);
		}
		Preference preference = new Preference(pType, preferedStartHour);
		double salesPercentage = 0;
		if (type == 3)
			salesPercentage = checkValidDoubleInput(strSalesPercentage);
		int salary = checkValidNumberInput(strSalary);
		for (Department d1 : departmentsList) {
			for (Role r1 : d1.getRolesList()) {
				for (Employee e1 : r1.getEmployeesList()) {
					if (e1.getId().equals(id))
						throw new Exception("This employee already exists.");
				}
			}
		}
		
		switch (type) {
		case 1: // GlobalSalaryEmployee
			GlobalSalaryEmployee e = new GlobalSalaryEmployee(name, id, preference, role, salary);
			role.addNewEmployee(e);
			fireCreateEmployeeModelEvent(e);
			break;
		case 2: // HourlyEmployee
			HourlyEmployee he = new HourlyEmployee(name, id, preference, role, salary);
			role.addNewEmployee(he);
			fireCreateEmployeeModelEvent(he);
			break;
		case 3: // SalesBonusEmployee
			SalesBonusEmployee sbe = new SalesBonusEmployee(name, id, preference, role, salary, salesPercentage);
			role.addNewEmployee(sbe);
			fireCreateEmployeeModelEvent(sbe);
			break;
		}
	}
	
	@Override
	public void changeDepartmentPref(Department department, PreferenceType pType, String startHour) throws Exception {
		if (department == null)
			throw new Exception("You must choose a department.");
		int preferedStartHour = 0;
		if (pType == PreferenceType.START_EARLY || pType == PreferenceType.START_LATE) {
			if (startHour == null) {
				throw new Exception("You must choose a department prefered start hour.");
			}
			String[] hourSplit = startHour.split(":", 2);
			preferedStartHour = Integer.parseInt(hourSplit[0]);
		}
		Preference preference = new Preference(pType, preferedStartHour);
		department.setPreference(preference);
		this.hasStartedExperiment = true;
	}
	
	@Override
	public void changeRolePref(Role role, PreferenceType pType, String startHour) throws Exception {
		if (role == null)
			throw new Exception("You must choose a role.");
		int preferedStartHour = 0;
		if (pType == PreferenceType.START_EARLY || pType == PreferenceType.START_LATE) {
			if (startHour == null) {
				throw new Exception("You must choose a department prefered start hour.");
			}
			String[] hourSplit = startHour.split(":", 2);
			preferedStartHour = Integer.parseInt(hourSplit[0]);
		}
		Preference preference = new Preference(pType, preferedStartHour);
		role.setPreference(preference);
		this.hasStartedExperiment = true;
	}
	
	@Override
	public void getCompanyEfficiency() {
		StringBuffer sb1 = new StringBuffer(toString());
		StringBuffer sb2 = new StringBuffer();
		double companyProfit = 0;
		if (hasStartedExperiment) {
		sb1.append("\nProfit by departments: ");
		int numOfRolles = 0, numOfEmployees = 0, index1 = 1;
		for (Department d : departmentsList) {
			sb1.append("\n" + (index1++) + ". " + d.getName() + ", Profit addition: " + d.getProfit() + "$\\day");
			companyProfit += d.getProfit();
			numOfRolles += d.getRolesList().size();
			int index2 = 0;
			for (Role r : d.getRolesList()) {
				numOfEmployees += r.getEmployeesList().size();
			}
		}
		sb1.append("\n\n============================================================\n");
		sb1.append("\nTotal number of departments: " + departmentsList.size());
		sb1.append("\nTotal number of rolles: " + numOfRolles);
		sb1.append("\nTotal number of employees: " + numOfEmployees);
		sb1.append("\n\n============================================================");
			if (companyProfit > 0)
				sb2.append("New Company Profit Addition: +" + companyProfit + "$\\day");
			else
				sb2.append("New Company Profit Addition: " + companyProfit + "$\\day");
		} else {
			sb1.append("\nWelcome to our company efficiency calculator.\n");
			sb2.append("You have to change a department or role\nwork method to start the experimnet.");
			companyProfit = -1;
		}

		fireCompanyEfficiencyModelEvent(sb1.toString(), sb2.toString(), companyProfit);
	}
	
	private void fireCreatedNewCompany() {
		for (SystemEventsListener l : allListeners)
			l.createNewCompanyModelEvent();
	}

	private void fireCreateDepartmentModelEvent(Department d) {
		for (SystemEventsListener l : allListeners)
			l.createDepartmentModelEvent(d);
	}

	private void fireCreateRoleModelEvent(Role r) {
		for (SystemEventsListener l : allListeners)
			l.createRoleModelEvent(r);
	}

	private void fireCreateEmployeeModelEvent(Employee e) {
		for (SystemEventsListener l : allListeners)
			l.createEmployeeModelEvent(e);
	}
	
	private void fireCompanyEfficiencyModelEvent(String name, String efficiency, double companyProfit) {
		for (SystemEventsListener l : allListeners)
			l.companyEfficiencyModelEvent(name, efficiency, companyProfit);
	}
	
	@Override
	public String toString() {
		return "Company: " + name;
	}
	
}
