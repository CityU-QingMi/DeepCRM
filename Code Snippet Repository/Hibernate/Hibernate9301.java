	public void afterSessionFactoryBuilt() {
		super.afterSessionFactoryBuilt();
		final Session session = sessionFactory().openSession();
		session.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						Statement st = ((SessionImplementor)session).getJdbcCoordinator().getStatementPreparer().createStatement();
						try {
							((SessionImplementor)session).getJdbcCoordinator().getResultSetReturn().execute( st, "drop table Point");
						}
						catch (Exception ignored) {
						}
						((SessionImplementor)session).getJdbcCoordinator().getResultSetReturn().execute( st, "create table Point (\"x\" number(19,2) not null, \"y\" number(19,2) not null, description varchar2(255) )");
						((SessionImplementor)session).getJdbcCoordinator().getResourceRegistry().release( st );
					}
				}
		);
		session.close();
	}
