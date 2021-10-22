package morganVazana_RoeeElkoubi.listeners;

import morganVazana_RoeeElkoubi.model.Department;
import morganVazana_RoeeElkoubi.model.Role;
import morganVazana_RoeeElkoubi.model.Employee.Employee;

public interface SystemEventsListener {

	void createDepartmentModelEvent(Department e);

	void createRoleModelEvent(Role e);

	void createEmployeeModelEvent(Employee e);

	void getFileCheckAnswer(boolean answer);

	void companyEfficiencyModelEvent(String name, String efficiency, double companyProfit);

	void createNewCompanyModelEvent();

}
