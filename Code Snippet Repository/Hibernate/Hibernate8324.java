	@Test
	public void testBatching() throws SQLException {
		doInHibernate( this::sessionFactory, session -> {
			Person worker = new Person();
			Person homestay = new Person();

			Address home = new Address();
			Address office = new Address();

			home.addPerson( homestay );

			office.addPerson( worker );

			session.persist( home );
			session.persist( office );

			connectionProvider.clear();
		} );

		PreparedStatement addressPreparedStatement = connectionProvider.getPreparedStatement(
				"insert into Address (ID) values (?)" );
		verify( addressPreparedStatement, times( 2 ) ).addBatch();
		verify( addressPreparedStatement, times( 1 ) ).executeBatch();
		PreparedStatement personPreparedStatement = connectionProvider.getPreparedStatement(
				"insert into Person (address_ID) values (?)" );
		verify( personPreparedStatement, times( 2 ) ).addBatch();
		verify( personPreparedStatement, times( 1 ) ).executeBatch();
	}
