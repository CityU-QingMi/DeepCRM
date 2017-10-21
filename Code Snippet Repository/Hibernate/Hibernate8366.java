	@Override
	protected void cleanupTest() throws Exception {
		try (Session session = openSession()) {
			session.doWork( connection -> {
				final Statement stmnt = connection.createStatement();

				stmnt.execute( getDialect().getDropTableString( "SANDBOX_JDBC_TST" ) );
			} );
		}
	}
