package morganVazana_RoeeElkoubi.controller;

import morganVazana_RoeeElkoubi.listeners.SystemEventsListener;
import morganVazana_RoeeElkoubi.listeners.SystemUIEventsListener;
import morganVazana_RoeeElkoubi.model.Department;
import morganVazana_RoeeElkoubi.model.ICompany;
import morganVazana_RoeeElkoubi.model.PreferenceType;
import morganVazana_RoeeElkoubi.model.Role;
import morganVazana_RoeeElkoubi.model.Employee.Employee;
import morganVazana_RoeeElkoubi.view.AbstractView;

public class SystemController implements SystemEventsListener, SystemUIEventsListener {
	private ICompany theModel;
	private AbstractView theView;

	public SystemController(ICompany theModel, AbstractView theView) {
		this.theModel = theModel;
		this.theView = theView;

		theModel.registerListener(this);
		theView.registerListener(this);
		checkReadFromFile();
	}
	
	private void checkReadFromFile() {
		try {
			theModel.checkReadFromFile();
			theView.successfulMessage("Data has been loaded from a saved file successfully");
		} catch (Exception e) {
			theView.successfulMessage(e.getMessage());
		}
	}

	@Override
	public void terminateProgramViewEvent() {
		try {
			theModel.terminateProgram();
			theView.successfulMessage("Data has been saved successfully");
		} catch (Exception e) {
			theView.exceptionMessage(e.getMessage());
		}
	}
	
	@Override
	public void createNewCompanyViewEvent(String name) {
		try {
			theModel.createNewCompany(name);
			theView.successfulMessage("Company has been created successfully");
		} catch (Exception e) {
			theView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void createDepartmentViewEvent(String name, boolean canChangeWorkHours,
			boolean syncedDepartment) {
		try {
			theModel.createDepartment(name, canChangeWorkHours, syncedDepartment);
			theView.successfulMessage("Deparment created successfully");
		} catch (Exception e) {
			theView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void createRoleViewEvent(String name, String strProfitPerEmployee, Department department,
			boolean canChangeWorkMethod, boolean syncronizedRole) {
		try {
			theModel.createRole(name, strProfitPerEmployee, department, canChangeWorkMethod, syncronizedRole);
			theView.successfulMessage("Role created successfully");
		} catch (Exception e) {
			theView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void createEmployeeViewEvent(int type, String name, String id, PreferenceType pType,
			String strPreferedStartHour, Role role, String strSalary, String strSalesPercentage) {
		try {
			theModel.createEmployee(type, name, id, pType, strPreferedStartHour, role, strSalary, strSalesPercentage);
			theView.successfulMessage("Employee created successfully");
		} catch (Exception e) {
			theView.exceptionMessage(e.getMessage());
		}
	}
	
	@Override
	public void changeDepartmentPref(Department department, PreferenceType pType, String startHour) {
		try {
			theModel.changeDepartmentPref(department, pType, startHour);
			theView.successfulMessage("Changed Department Preference Succesfully.");
		} catch (Exception e) {
			theView.exceptionMessage(e.getMessage());
		}
		
	}
	
	@Override
	public void changeRolePref(Role role, PreferenceType pType, String startHour) {
		try {
			theModel.changeRolePref(role, pType, startHour);
			theView.successfulMessage("Changed Role Preference Succesfully.");
		} catch (Exception e) {
			theView.exceptionMessage(e.getMessage());
		}
	}
	
	@Override
	public void getCompanyEfficiencyViewEvent() {
		try {
			theModel.getCompanyEfficiency();
		} catch (Exception e) {
			theView.exceptionMessage(e.getMessage());
		}	
	}
	
	@Override
	public void getFileCheckAnswer(boolean answer) {
		theView.getFileCheckAnswer(answer);
	}
	
	@Override
	public void createNewCompanyModelEvent() {
		theView.createNewCompanyModelEvent();
	}

	@Override
	public void createDepartmentModelEvent(Department d) {
		theView.createDepartmentModelEvent(d);
		
	}

	@Override
	public void createRoleModelEvent(Role r) {
		theView.createRoleModelEvent(r);
		
	}
	
	@Override
	public void createEmployeeModelEvent(Employee e) {
		theView.createEmployeeModelEvent(e);
	}
	
	@Override
	public void companyEfficiencyModelEvent(String name, String efficiency, double companyProfit) {
		theView.getCompanyEfficiencyModelEvent(name, efficiency, companyProfit);
	}

}
