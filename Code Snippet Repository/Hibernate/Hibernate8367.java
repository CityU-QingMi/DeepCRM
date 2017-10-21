	@Test
	public void testSessionFactorySetting() throws SQLException {
		Session session = sessionFactory().openSession();
		session.beginTransaction();
		try {
			addEvents( session );
		}
		finally {
			connectionProvider.clear();
			session.getTransaction().commit();
			session.close();
		}
		PreparedStatement preparedStatement = connectionProvider.getPreparedStatement(
				"insert into Event (name, id) values (?, ?)" );
		verify( preparedStatement, times( 5 ) ).addBatch();
		verify( preparedStatement, times( 3 ) ).executeBatch();
	}
