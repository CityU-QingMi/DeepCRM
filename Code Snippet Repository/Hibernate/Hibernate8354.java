	@Test
	public void testGeneralUsage() throws Throwable {
		final Session session = openSession();
		session.beginTransaction();
		session.doWork(
				new Work() {
					public void execute(Connection connection) throws SQLException {
						// in this current form, users must handle try/catches themselves for proper resource release
						Statement statement = null;
						try {
							statement = ((SessionImplementor)session).getJdbcCoordinator().getStatementPreparer().createStatement();
							ResultSet resultSet = null;
							try {
								
								resultSet = ((SessionImplementor)session).getJdbcCoordinator().getResultSetReturn().extract( statement, "select * from T_JDBC_PERSON" );
							}
							finally {
								releaseQuietly( ((SessionImplementor)session), resultSet, statement );
							}
							try {
								((SessionImplementor)session).getJdbcCoordinator().getResultSetReturn().extract( statement, "select * from T_JDBC_BOAT" );
							}
							finally {
								releaseQuietly( ((SessionImplementor)session), resultSet, statement );
							}
						}
						finally {
							releaseQuietly( ((SessionImplementor)session), statement );
						}
					}
				}
		);
		session.getTransaction().commit();
		session.close();
	}
