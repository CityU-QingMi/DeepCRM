	@Test
	public void testDriverRegistrationUsingLoadClassFails() {
		final String driverClassName = "org.hibernate.connection.DriverManagerRegistrationTest$TestDriver1";
		final String url = "jdbc:hibernate:test";

		try {
			determineClassLoader().loadClass( driverClassName );
		}
		catch (ClassNotFoundException e) {
			fail( "Error loading JDBC Driver class : " + e.getMessage() );
		}

		try {
			DriverManager.getDriver( url );
			fail( "This test should have failed to locate JDBC driver per HHH-7272" );
		}
		catch (SQLException expected) {
			// actually this should fail due to the reasons discussed on HHH-7272
		}
	}
