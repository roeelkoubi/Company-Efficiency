package morganVazana_RoeeElkoubi.model;

import morganVazana_RoeeElkoubi.listeners.SystemEventsListener;
import morganVazana_RoeeElkoubi.model.Employee.Employee;

public interface ICompany {

	void registerListener(SystemEventsListener l);
	
	void addHardCodedObjects() throws Exception;

	void createDepartment(String name, boolean canChangeWorkHours, boolean syncedDepartment) throws Exception;

	void createRole(String name, String strProfitPerEmployee, Department department, boolean canChangeWorkMethod,
			boolean syncronizedRole) throws Exception;

	void createEmployee(int type, String name, String id, PreferenceType pType, String strPreferedStartHour, Role role, String strSalary,
			 String strSalesPercentage) throws Exception;

	void changeDepartmentPref(Department department, PreferenceType pType, String startHour) throws Exception;

	void changeRolePref(Role role, PreferenceType pType, String startHour) throws Exception;

	void terminateProgram() throws Exception;

	void checkReadFromFile() throws Exception;

	void createNewCompany(String name) throws Exception;

	void getCompanyEfficiency();

}
