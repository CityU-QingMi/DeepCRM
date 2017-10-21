	@Test
	public void testBadGrammar() throws Exception {
		final Session session = openSession();
		session.beginTransaction();

		session.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						// prepare/execute a query against a non-existent table
						PreparedStatement ps = null;
						try {
							ps = ((SessionImplementor)session).getJdbcCoordinator().getStatementPreparer().prepareStatement( "SELECT user_id, user_name FROM tbl_no_there" );
							((SessionImplementor)session).getJdbcCoordinator().getResultSetReturn().extract( ps );

							fail("SQL compilation should have failed");
						}
						catch (SQLGrammarException ignored) {
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
