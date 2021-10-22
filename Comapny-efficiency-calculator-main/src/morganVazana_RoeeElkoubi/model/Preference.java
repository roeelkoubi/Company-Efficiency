package morganVazana_RoeeElkoubi.model;

import java.io.Serializable;

public class Preference implements Serializable {
	private PreferenceType type;
	private int preferedStartHour;
	private final int Global_Start_Hour = 8;
	
	public Preference(PreferenceType type, int preferedStartHour) {
		this.type = type;
		this.preferedStartHour = setStartHour(preferedStartHour);
	}
	
	private int setStartHour(int preferedStartHour) {
		if (type == PreferenceType.START_EARLY || type == PreferenceType.START_LATE)
			return preferedStartHour;
		else
			return Global_Start_Hour;
	}

	public PreferenceType getType() {
		return type;
	}

	public int getPreferedStartHour() {
		return preferedStartHour;
	}
	
	public int getGlobal_Start_Hour() {
		return Global_Start_Hour;
	}

	public double getEfficiency() {
		if (type == PreferenceType.START_EARLY || type == PreferenceType.START_LATE)
			return 0.2;
		else if (type == PreferenceType.WORK_FROM_HOME)
			return 0.1;
		else
			return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Preference)) {
			return false;
		}
		Preference other = (Preference) obj;
		return type == other.type;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Preference type: " + type + "\nPrefered start hour: ");
		if (type == PreferenceType.START_EARLY || type == PreferenceType.START_LATE) {
			if (preferedStartHour < 10)
				sb.append("0" + preferedStartHour + ":00");
			else
				sb.append(preferedStartHour + ":00");
		} else
			sb.append("08:00");
		return sb.toString();
	}
	
	
}
