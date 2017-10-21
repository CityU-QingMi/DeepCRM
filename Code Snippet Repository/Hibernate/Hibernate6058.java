	@Override
	public void releaseResources() {
		Session s = entityManagerFactory().unwrap( SessionFactory.class ).openSession();
		s.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						connection.createStatement().execute( "DROP ALIAS allEmployeeNames IF EXISTS" );
					}
				}
		);
		s.close();

		super.releaseResources();
	}
