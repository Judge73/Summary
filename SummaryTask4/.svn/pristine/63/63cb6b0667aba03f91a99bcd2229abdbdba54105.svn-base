package ua.nure.kotkov.SummaryTask4.db;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ua.nure.kotkov.SummaryTask4.db.entity.User;

public class RoleTest {

	@Test
	public void testInit() {
		assertTrue(Role.ADMIN.equals(Role.valueOf("ADMIN")));
	}
	
	@Test
	public void testGetRole() {
		User user = mock(User.class);
		expect(user.getRoleId()).andReturn((long) 1);
		replay(user);
		assertTrue(Role.DISPATCHER.equals(Role.getRole(user)));
	}
	
	@Test
	public void testGetName() {
		assertTrue("dispatcher".equals(Role.DISPATCHER.getName()));
	}

}
