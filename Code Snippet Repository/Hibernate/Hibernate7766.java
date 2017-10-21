	public void testIntegrityViolation() throws Exception {
		final Session session = openSession();
		session.beginTransaction();

		session.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						// Attempt to insert some bad values into the T_MEMBERSHIP table that should
						// result in a constraint violation
						PreparedStatement ps = null;
						try {
							ps = ((SessionImplementor)session).getJdbcCoordinator().getStatementPreparer().prepareStatement( "INSERT INTO T_MEMBERSHIP (user_id, group_id) VALUES (?, ?)" );
							ps.setLong(1, 52134241);    // Non-existent user_id
							ps.setLong(2, 5342);        // Non-existent group_id
							((SessionImplementor)session).getJdbcCoordinator().getResultSetReturn().executeUpdate( ps );

							fail("INSERT should have failed");
						}
						catch (ConstraintViolationException ignore) {
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
