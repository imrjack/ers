package com.revature.ers;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectionFactoryTest {

	@Test
	public void test() {
		try {
			assertNotNull(ConnectionFactory.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
