	@AfterClass
	public static void afterwards() {
		try {
			DriverManager.deregisterDriver( TestDriver1.INSTANCE );
		}
		catch (SQLException ignore) {
		}
		try {
			DriverManager.deregisterDriver( TestDriver2.INSTANCE );
		}
		catch (SQLException ignore) {
		}
	}
