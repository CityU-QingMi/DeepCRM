	@Test
	public void test() {

		doInHibernate( this::sessionFactory, session -> {
			sqlStatementInterceptor.getSqlQueries().clear();
			List<Person> persons = session.createQuery(
				"select distinct p from Person p" )
			.getResultList();
			String sqlQuery = sqlStatementInterceptor.getSqlQueries().getLast();
			assertTrue( sqlQuery.contains( " distinct " ) );
		} );

		doInHibernate( this::sessionFactory, session -> {
			sqlStatementInterceptor.getSqlQueries().clear();
			List<Person> persons = session.createQuery(
				"select distinct p from Person p" )
			.setHint( QueryHints.HINT_PASS_DISTINCT_THROUGH, false )
			.getResultList();
			String sqlQuery = sqlStatementInterceptor.getSqlQueries().getLast();
			assertFalse( sqlQuery.contains( " distinct " ) );
		} );

		doInHibernate( this::sessionFactory, session -> {
			List<Person> persons = session.createQuery(
				"select p from Person p left join fetch p.phones " )
			.getResultList();
			assertEquals(2, persons.size());
		} );

		doInHibernate( this::sessionFactory, session -> {
			sqlStatementInterceptor.getSqlQueries().clear();
			List<Person> persons = session.createQuery(
				"select distinct p from Person p left join fetch p.phones " )
			.getResultList();
			assertEquals(1, persons.size());
			String sqlQuery = sqlStatementInterceptor.getSqlQueries().getLast();
			assertTrue( sqlQuery.contains( " distinct " ) );
		} );
	}
