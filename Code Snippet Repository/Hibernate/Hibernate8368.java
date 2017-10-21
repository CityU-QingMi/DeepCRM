	@Test
	public void testSessionSettingOverridesSessionFactorySetting()
			throws SQLException {
		Session session = sessionFactory().openSession();
		session.setJdbcBatchSize( 3 );
		session.beginTransaction();
		try {
			addEvents( session );
		}
		finally {
			connectionProvider.clear();
			session.getTransaction().commit();
			session.close();
		}

		PreparedStatement preparedStatement = connectionProvider.getPreparedStatement( "insert into Event (name, id) values (?, ?)" );
		verify(preparedStatement, times( 5 )).addBatch();
		verify(preparedStatement, times( 2 )).executeBatch();

		session = sessionFactory().openSession();
		session.setJdbcBatchSize( null );
		session.beginTransaction();
		try {
			addEvents( session );
		}
		finally {
			connectionProvider.clear();
			session.getTransaction().commit();
			session.close();
		}
		List<PreparedStatement> preparedStatements = connectionProvider.getPreparedStatements();
		assertEquals(1, preparedStatements.size());
		preparedStatement = preparedStatements.get( 0 );
		verify(preparedStatement, times( 5 )).addBatch();
		verify(preparedStatement, times( 3 )).executeBatch();
	}
