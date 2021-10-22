package morganVazana_RoeeElkoubi.listeners;

import morganVazana_RoeeElkoubi.model.Department;
import morganVazana_RoeeElkoubi.model.PreferenceType;
import morganVazana_RoeeElkoubi.model.Role;
import morganVazana_RoeeElkoubi.model.Employee.Employee;

public interface SystemUIEventsListener {

	void createDepartmentViewEvent(String name, boolean canChangeWorkHours, boolean syncedDepartment);

	void createRoleViewEvent(String name, String strProfitPerEmployee, Department department, boolean canChangeWorkMethod,
			boolean syncronizedRole);

	void createEmployeeViewEvent(int type, String name, String id, PreferenceType pType,
			String strPreferedStartHour, Role role, String strSalary, String strSalesPercentage);

	void changeDepartmentPref(Department department, PreferenceType pType, String startHour);

	void changeRolePref(Role role, PreferenceType pType, String startHour);

	void terminateProgramViewEvent();

	void createNewCompanyViewEvent(String name);

	void getCompanyEfficiencyViewEvent();
	
}
