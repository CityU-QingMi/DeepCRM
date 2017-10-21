	@Test
	public void testDriverRegistrationUsingClassForNameSucceeds() {
		final String driverClassName = "org.hibernate.connection.DriverManagerRegistrationTest$TestDriver2";
		final String url = "jdbc:hibernate:test2";
		try {
			Class.forName( driverClassName, true, determineClassLoader() );
		}
		catch (ClassNotFoundException e) {
			fail( "Error loading JDBC Driver class : " + e.getMessage() );
		}

		try {
			assertNotNull( DriverManager.getDriver( url ) );
		}
		catch (SQLException expected) {
			fail( "Unanticipated failure according to HHH-7272" );
		}
	}
