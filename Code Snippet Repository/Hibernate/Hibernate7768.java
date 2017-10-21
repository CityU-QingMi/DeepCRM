	@Test
	@TestForIssue(jiraKey = "")
	public void testNotNullConstraint() {
		final Session session = openSession();
		session.beginTransaction();

		final User user = new User();
		user.setUsername( "Lukasz" );
		session.save( user );
		session.flush();

		session.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						final JdbcCoordinator jdbcCoordinator = ( (SessionImplementor) session ).getJdbcCoordinator();
						final StatementPreparer statementPreparer = jdbcCoordinator.getStatementPreparer();
						final ResultSetReturn resultSetReturn = jdbcCoordinator.getResultSetReturn();
						PreparedStatement ps = null;
						try {
							ps = statementPreparer.prepareStatement( "UPDATE T_USER SET user_name = ? WHERE user_id = ?" );
							ps.setNull( 1, Types.VARCHAR ); // Attempt to update user name to NULL (NOT NULL constraint defined).
							ps.setLong( 2, user.getId() );
							resultSetReturn.executeUpdate( ps );

							fail( "UPDATE should have failed because of not NULL constraint." );
						}
						catch ( ConstraintViolationException ignore ) {
							// expected outcome
						}
						finally {
							releaseStatement( session, ps );
						}
					}
				}
		);

		session.getTransaction().rollback();
		session.close();
	}
