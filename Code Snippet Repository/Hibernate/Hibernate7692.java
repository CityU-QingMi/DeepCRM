	private void executeUpdateSafety(Session session, String query) {
		session.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						connection.createStatement().execute( query );
					}
				}
		);
	}
