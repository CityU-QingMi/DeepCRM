	@Test
	public void testBatching() throws SQLException {
		doInHibernate( this::sessionFactory, session -> {
			Person father = new Person();
			Person mother = new Person();
			Person son = new Person();
			Person daughter = new Person();

			Address home = new Address();
			Address office = new Address();

			home.addPerson( father );
			home.addPerson( mother );
			home.addPerson( son );
			home.addPerson( daughter );

			office.addPerson( father );
			office.addPerson( mother );

			session.persist( home );
			session.persist( office );

			connectionProvider.clear();
		} );

		assertEquals( 3, connectionProvider.getPreparedStatements().size() );
		PreparedStatement addressPreparedStatement = connectionProvider.getPreparedStatement(
				"insert into Address (ID) values (?)" );
		verify( addressPreparedStatement, times( 2 ) ).addBatch();
		verify( addressPreparedStatement, times( 1 ) ).executeBatch();
		PreparedStatement personPreparedStatement = connectionProvider.getPreparedStatement(
				"insert into Person (ID) values (?)" );
		verify( personPreparedStatement, times( 4 ) ).addBatch();
		verify( personPreparedStatement, times( 1 ) ).executeBatch();
	}
