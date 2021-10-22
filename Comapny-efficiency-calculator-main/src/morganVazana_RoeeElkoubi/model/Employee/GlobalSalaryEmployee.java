package morganVazana_RoeeElkoubi.model.Employee;

import morganVazana_RoeeElkoubi.model.Preference;
import morganVazana_RoeeElkoubi.model.Role;

public class GlobalSalaryEmployee extends Employee {
	private int globalSalary;

	public GlobalSalaryEmployee(String name, String id, Preference preference, Role role, int globalSalary) {
		super(name, id, preference, role);
		this.globalSalary = globalSalary;
	}
	
	@Override
	public String getEmployeeInformation() {
		StringBuffer sb = new StringBuffer(super.getEmployeeInformation());
		sb.append("Salary: " + globalSalary + "$\\month\n");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Global Salary Employee: " + super.toString();
	}

}
