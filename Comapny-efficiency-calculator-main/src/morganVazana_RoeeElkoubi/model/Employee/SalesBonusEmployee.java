package morganVazana_RoeeElkoubi.model.Employee;

import morganVazana_RoeeElkoubi.model.Preference;
import morganVazana_RoeeElkoubi.model.Role;

public class SalesBonusEmployee extends GlobalSalaryEmployee {
	private double salesPercentage;

	public SalesBonusEmployee(String name,String id, Preference preference, Role role, int globalSalary, double salesPercentage) {
		super(name, id, preference, role, globalSalary);
		this.salesPercentage = salesPercentage;
	}
	
	@Override
	public String getEmployeeInformation() {
		StringBuffer sb = new StringBuffer(super.getEmployeeInformation());
		sb.append("Sales percentage: " + salesPercentage + "%\n");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "Sales Bonus + " + super.toString();
	}

}
