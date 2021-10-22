package morganVazana_RoeeElkoubi.model.Employee;

import morganVazana_RoeeElkoubi.model.Preference;
import morganVazana_RoeeElkoubi.model.Role;

public class HourlyEmployee extends Employee {
	private int salaryPerHour;

	public HourlyEmployee(String name,String id, Preference preference, Role role, int salaryPerHour) {
		super(name, id, preference, role);
		this.salaryPerHour = salaryPerHour;
	}
	
	@Override
	public String getEmployeeInformation() {
		StringBuffer sb = new StringBuffer(super.getEmployeeInformation());
		sb.append("Salary per hour: " + salaryPerHour + "$\\hour\n");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "Hourly Employee: " + super.toString();
	}
}
