	@Test
	public void testSQLExceptionThrowing() {
		final Session session = openSession();
		session.beginTransaction();
		try {
			session.doWork(
					new Work() {
						public void execute(Connection connection) throws SQLException {
							Statement statement = null;
							try {
								statement = ((SessionImplementor)session).getJdbcCoordinator().getStatementPreparer().createStatement();
								((SessionImplementor)session).getJdbcCoordinator().getResultSetReturn().extract( statement, "select * from non_existent" );
							}
							finally {
								releaseQuietly( ((SessionImplementor)session), statement );
							}
						}
					}
			);
			fail( "expecting exception" );
		}
		catch ( JDBCException expected ) {
			// expected outcome
		}
		session.getTransaction().commit();
		session.close();
	}
