package morganVazana_RoeeElkoubi.model.Employee;

import java.io.Serializable;
import java.text.DecimalFormat;
import morganVazana_RoeeElkoubi.model.Preference;
import morganVazana_RoeeElkoubi.model.PreferenceType;
import morganVazana_RoeeElkoubi.model.Role;
import morganVazana_RoeeElkoubi.model.Syncronizable;
import morganVazana_RoeeElkoubi.model.workMethodChangeable;

public abstract class Employee implements Syncronizable, workMethodChangeable, Serializable {
	protected String name;
	protected String id;
	protected Preference preference;
	protected Role role;
	protected int employeeID;
	protected static int employeeIDGenerator = 1;
	protected boolean canChangeWorkMethod;
	protected boolean syncronizedEmployee;
	private DecimalFormat df = new DecimalFormat("#.0");

	public Employee(String name, String id, Preference preference, Role role) {
		this.name = name;
		this.id = id;
		this.preference = preference;
		this.role = role;
		this.employeeID = employeeIDGenerator++;
		this.canChangeWorkMethod = true;
		this.syncronizedEmployee = false;
	}
	
	public double calculateEfficiency() {
		if (!(role.getDepartment().isWorkMethodChangeable())) {
			if (preference.getType() == PreferenceType.WORK_FROM_HOME
					&& role.getDepartment().getPreference().getType() != PreferenceType.WORK_FROM_HOME)
				return 8 * (-0.1);
			else if (preference.getType() == PreferenceType.WORK_FROM_HOME
					&& role.getDepartment().getPreference().getType() == PreferenceType.WORK_FROM_HOME)
				return 8 * 0.1;

			return calculateHourEfficiency(preference.getPreferedStartHour(),
					role.getDepartment().getPreference().getPreferedStartHour(),
					role.getDepartment().getPreference().getType());
		} else {
			if (!(role.isWorkMethodChangeable())) {
				if (preference.getType() == PreferenceType.WORK_FROM_HOME
						&& role.getPreference().getType() != PreferenceType.WORK_FROM_HOME)
					return 8 * (-0.1);
				else if (preference.getType() == PreferenceType.WORK_FROM_HOME
						&& role.getPreference().getType() == PreferenceType.WORK_FROM_HOME)
					return 8 * 0.1;

				return calculateHourEfficiency(preference.getPreferedStartHour(),
						role.getPreference().getPreferedStartHour(), role.getPreference().getType());
			} else {
				if (role.getDepartment().isSyncronizable()) {
					if (preference.getType() == PreferenceType.WORK_FROM_HOME
							&& role.getDepartment().getPreference().getType() != PreferenceType.WORK_FROM_HOME)
						return 8 * (-0.1);
					else if (preference.getType() == PreferenceType.WORK_FROM_HOME
							&& role.getDepartment().getPreference().getType() == PreferenceType.WORK_FROM_HOME)
						return 8 * 0.1;

					return calculateHourEfficiency(preference.getPreferedStartHour(),
							role.getDepartment().getPreference().getPreferedStartHour(),
							role.getDepartment().getPreference().getType());
				} else {
					if (role.isSyncronizable()) {
						if (preference.getType() == PreferenceType.WORK_FROM_HOME
								&& role.getPreference().getType() != PreferenceType.WORK_FROM_HOME)
							return 8 * (-0.1);
						else if (preference.getType() == PreferenceType.WORK_FROM_HOME
								&& role.getPreference().getType() == PreferenceType.WORK_FROM_HOME)
							return 8 * 0.1;

						return calculateHourEfficiency(preference.getPreferedStartHour(),
								role.getPreference().getPreferedStartHour(), role.getPreference().getType());
					} else {
						if (preference.getType() == PreferenceType.WORK_FROM_HOME)
							return 8 * 0.1;
						else
							return Math.abs(preference.getGlobal_Start_Hour() - preference.getPreferedStartHour()) * 0.2;
					}
				}
			}
		}
	}
	
	public double calculateHourEfficiency(int desiredStartHour, int actualStartHour, PreferenceType actualType) {
		int[] desiredWorkHours = new int[8];
		int[] actualWorkHours = new int[8];
		int commonHours = 0, uncommonHours = 0;
		for (int i = 0; i < actualWorkHours.length; i++) {
			desiredWorkHours[i] = desiredStartHour++;
			actualWorkHours[i] = actualStartHour++;
		}
		if (preference.getType() == PreferenceType.START_EARLY && actualType == PreferenceType.START_EARLY) {
			for (int i = 0; i < desiredWorkHours.length; i++) {
				int counter = 0;
				for (int j = 0; j < actualWorkHours.length; j++) {
					if (desiredWorkHours[i] > 15 || desiredWorkHours[i] < 8)
						if (desiredWorkHours[i] == actualWorkHours[j])
							commonHours++;
						else
							counter++;
					else
						continue;
				}
				if (counter == 8)
					uncommonHours++;
			}
			return commonHours * 0.2 + uncommonHours * (-0.2);
		}
		if (desiredStartHour < actualStartHour) {
			for (int i = 0; i < desiredWorkHours.length; i++) {
				int counter = 0;
				for (int j = 0; j < actualWorkHours.length; j++) {
					if (desiredWorkHours[i] > 15 || desiredWorkHours[i] < 8)
						if (desiredWorkHours[i] == actualWorkHours[j])
							commonHours++;
						else
							counter++;
					else
						continue;
				}
				if (counter == 8)
					uncommonHours++;
			}
			return commonHours * 0.2 + uncommonHours * (-0.2);
		}
		else {
			for (int i = 0; i < desiredWorkHours.length; i++) {
				int counter = 0;
				for (int j = 0; j < actualWorkHours.length; j++) {
					if (actualWorkHours[i] > 15 || actualWorkHours[i] < 8)
						if (actualWorkHours[i] == desiredWorkHours[j])
							commonHours++;
						else
							counter++;
					else
						continue;
				}
				if (counter == 8)
					uncommonHours++;
			}
			return commonHours * 0.2 + uncommonHours * (-0.2);
		}
	}
	

	@Override
	public boolean isWorkMethodChangeable() {
		return canChangeWorkMethod;
	}

	@Override
	public boolean isSyncronizable() {
		return syncronizedEmployee;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmployeeInformation() {
		StringBuffer sb = new StringBuffer(toString() + "\n");
		sb.append(role.toString() + "\n");
		sb.append(preference.toString() + "\n");
		return sb.toString();
	}
	
	public double getProfit() {
		return Double.parseDouble(df.format(role.getProfitPerEmployee() * calculateEfficiency()));
	}
	
	public String getEfficiency() {
		StringBuffer sb = new StringBuffer(toString());
		sb.append("\nEmployee's preference: " + preference);
		sb.append("\nDepartment " + role.getDepartment().getName() + ", Preference: " + role.getDepartment().getPreference().toString());
		sb.append("\nRole " + role.getName() +", Prefernece: " + role.getPreference());
		sb.append("\nProfit addition from this employee: " + getProfit() + "$\\day");
		return sb.toString();
	}
	
	public static void setEmployeeIDGenerator(int serial) {
		Employee.employeeIDGenerator = serial;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		return this.id.equals(other.getId());
	}

	@Override
	public String toString() {
		return name + ", I.D:" + id + ", Employee I.D: #" + employeeID;
	}
	
}
