package ua.nure.kotkov.SummaryTask4.db;

import ua.nure.kotkov.SummaryTask4.db.entity.User;

/**
 * Role entity.
 * 
 * @author D.Kolesnikov
 * 
 */

public enum Role {
	ADMIN, DISPATCHER;
	
	public static Role getRole(User user) {
		int roleId = (int) user.getRoleId();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
