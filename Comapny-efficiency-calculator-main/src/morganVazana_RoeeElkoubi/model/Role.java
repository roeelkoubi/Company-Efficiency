package morganVazana_RoeeElkoubi.model;

import java.io.Serializable;
import java.util.ArrayList;

import morganVazana_RoeeElkoubi.model.Employee.Employee;

public class Role implements Syncronizable, workMethodChangeable, Serializable {
	private String name;
	private int profitPerEmployee;
	private Department department;
	private Preference preference;
	private boolean canChangeWorkMethod;
	private boolean syncronizedRole;
	private ArrayList<Employee> employeesList;

	public Role(String name,int profitPerEmployee, Department department, boolean canChangeWorkMethod,
			boolean syncronizedRole) {
		this.name = name;
		this.profitPerEmployee = profitPerEmployee;
		this.department = department;
		this.preference = new Preference(PreferenceType.NO_CHANGE, 8);
		this.canChangeWorkMethod = canChangeWorkMethod;
		this.syncronizedRole = syncronizedRole;
		this.employeesList = new ArrayList<Employee>();
	}

	@Override
	public boolean isWorkMethodChangeable() {
		return canChangeWorkMethod;
	}

	@Override
	public boolean isSyncronizable() {
		return syncronizedRole;
	}
	
	public void addNewEmployee(Employee e) throws Exception {
		for (Employee e1 : employeesList) {
			if (e.equals(e1))
				throw new Exception("Employee already exists.");
		}
		employeesList.add(e);
	}

	public String getName() {
		return name;
	}

	public Department getDepartment() {
		return department;
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}

	public boolean canChangeWorkMethod() {
		return canChangeWorkMethod;
	}

	public ArrayList<Employee> getEmployeesList() {
		return employeesList;
	}

	public int getProfitPerEmployee() {
		return profitPerEmployee;
	}

	public String getRoleInformation() {
		StringBuffer sb = new StringBuffer ("Role: " + name);
		sb.append("\nProfit per employee: " + profitPerEmployee + "$");
		sb.append("\nCan change work method: " + canChangeWorkMethod);
		sb.append("\nSyncronized role: " + syncronizedRole);
		if (preference == null || preference.getType() == PreferenceType.NO_CHANGE)
			sb.append("\nShift start hour: 08:00");
		else if (preference.getType() == PreferenceType.WORK_FROM_HOME)
			sb.append("\nShift start hour is according to the employees convenience.");
		else
			if (preference.getPreferedStartHour() < 10)
				sb.append("\nShift start hour: 0" + preference.getPreferedStartHour() + ":00");
			else
				sb.append("\nShift start hour: " + preference.getPreferedStartHour() + ":00");
		sb.append("\nEmployees list:\n");
		int i = 1;
		for (Employee e : employeesList)
			sb.append((i++) + ". " + e.toString() + "\n");
		return sb.toString();
	}
	
	public double getRoleProfit() {
		double profit = 0;
		for (Employee e : employeesList)
			profit += e.getProfit();
		return profit;
	}
	
	public double getRoleEfficiency() {
		double efficiency = 0;
		for (Employee e : employeesList)
			efficiency += e.calculateEfficiency();
		return efficiency;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Role)) {
			return false;
		}
		Role other = (Role) obj;
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
		return "Role: " + name + ", Department: " + department.getName();
	}
	
}
