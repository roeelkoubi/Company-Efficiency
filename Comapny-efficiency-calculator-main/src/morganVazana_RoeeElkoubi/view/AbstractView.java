package morganVazana_RoeeElkoubi.view;

import morganVazana_RoeeElkoubi.listeners.SystemUIEventsListener;
import morganVazana_RoeeElkoubi.model.Department;
import morganVazana_RoeeElkoubi.model.Role;
import morganVazana_RoeeElkoubi.model.Employee.Employee;

public interface AbstractView {

	void registerListener(SystemUIEventsListener l);

	void successfulMessage(String message);

	void exceptionMessage(String message);

	void createDepartmentModelEvent(Department d);

	void createRoleModelEvent(Role r);
	
	void createEmployeeModelEvent(Employee e);

	void getFileCheckAnswer(boolean answer);

	void getCompanyEfficiencyModelEvent(String name, String efficiency, double companyProfit);

	void createNewCompanyModelEvent();

}
