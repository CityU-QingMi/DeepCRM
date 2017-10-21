	@Test
	public void testRegisteredNamedSQLQueryWithScalar()
	{
		final NamedSQLQueryDefinitionBuilder builder = new NamedSQLQueryDefinitionBuilder();
		builder.setName("namedQuery");
		builder.setQuery("select count(*) AS c from ORGANIZATION");
		builder.setQueryReturns(new NativeSQLQueryReturn[1]);
		
		sessionFactory().registerNamedSQLQueryDefinition("namedQuery", builder.createNamedQueryDefinition());

		final Session s = openSession();
		s.beginTransaction();
		final SQLQuery query = (SQLQuery) s.getNamedQuery("namedQuery");
		query.addScalar("c");
		final Number result = (Number) query.uniqueResult();
 		s.getTransaction().commit();
		s.close();
		
		assertNotNull(result);
		assertTrue(0 == result.intValue());
	}
