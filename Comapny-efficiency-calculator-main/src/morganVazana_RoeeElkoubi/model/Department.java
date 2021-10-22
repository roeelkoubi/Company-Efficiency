package morganVazana_RoeeElkoubi.model;

import java.io.Serializable;
import java.util.ArrayList;

import morganVazana_RoeeElkoubi.model.Employee.Employee;

public class Department implements Syncronizable, workMethodChangeable, Serializable {
	private String name;
	private Preference preference;
	private boolean canChangeWorkMethod;
	private boolean syncedDepartment;
	private ArrayList<Role> rolesList;
	
	public Department (String name, boolean canChangeWorkHours, boolean syncedDepartment) {
		this.name = name;
		this.preference = new Preference(PreferenceType.NO_CHANGE, 8);
		this.canChangeWorkMethod = canChangeWorkHours;
		this.syncedDepartment = syncedDepartment;
		this.rolesList = new ArrayList<Role>();
	}

	@Override
	public boolean isWorkMethodChangeable() {
		return canChangeWorkMethod;
	}

	@Override
	public boolean isSyncronizable() {
		return syncedDepartment;
	}
	
	public void addNewRole(Role r) throws Exception {
		for (Role r1 : rolesList) {
			if (r1.equals(r))
				throw new Exception("This role already exists");
		}
		rolesList.add(r);
	}

	public String getName() {
		return name;
	}

	public Preference getPreference() {
		return preference;
	}

	public boolean canChangeWorkMethod() {
		return canChangeWorkMethod;
	}

	public ArrayList<Role> getRolesList() {
		return rolesList;
	}
	
	public void setPreference(Preference preference) {
		this.preference = preference;
	}
	
	public String getDepartmentInformation() {
		StringBuffer sb = new StringBuffer ("Department: " + name);
		sb.append("\nCan change work method: " + canChangeWorkMethod);
		sb.append("\nSyncronized department: " + syncedDepartment);
		if (preference == null || preference.getType() == PreferenceType.NO_CHANGE)
			sb.append("\nShift start hour: 08:00");
		else if (preference.getType() == PreferenceType.WORK_FROM_HOME)
			sb.append("\nShift start hour is according to the employees convenience.");
		else
			if (preference.getPreferedStartHour() < 10)
				sb.append("\nShift start hour: 0" + preference.getPreferedStartHour() + ":00");
			else
				sb.append("\nShift start hour: " + preference.getPreferedStartHour() + ":00");
		sb.append("\nRole list:\n");
		int i = 1;
		for (Role r : rolesList)
			sb.append((i++) + ". " + r.getName() + "\n");
		return sb.toString();
	}
	
	public double getProfit() {
		double profit = 0;
		for (Role r : rolesList)
			for (Employee e : r.getEmployeesList())
				profit += e.getProfit();
		return profit;
	}
	
	public double getEfficiency() {
		double efficiency = 0;
		for (Role r : rolesList)
			for (Employee e : r.getEmployeesList())
				efficiency += e.calculateEfficiency();
		return efficiency;
	}
	
	public String getDepartmentEfficiency() {
		StringBuffer sb = new StringBuffer(toString());
		sb.append("\nProfit by roles: ");
		int numOfEmployees = 0, index = 1;
		double departmentProfit = 0;
		for (Role r : rolesList) {
			sb.append("\n" + (index++) + ". " + r.getName() + ", Profit addition: " + r.getRoleProfit() + "$\\day");
			numOfEmployees += r.getEmployeesList().size();
			departmentProfit += r.getRoleProfit();
			}
		sb.append("\n\n============================================================\n");
		sb.append("\nTotal number of roles: " + rolesList.size());
		sb.append("\nTotal number of employees: " + numOfEmployees);
		sb.append("\nNew profit addition from this department: " + departmentProfit + "$\\day");
		return sb.toString();
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Department)) {
			return false;
		}
		Department other = (Department) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Department: " + name;
	}
	
}
