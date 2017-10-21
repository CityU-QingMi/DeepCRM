	@Test
	public void testPartialScalarAutoDiscovery() {
		prepareTestData();

		Session s = openSession();
		s.beginTransaction();
		s.createSQLQuery( queryString )
				.setResultSetMapping( "explicitScalarResultSetMapping" )
				.list();
		s.getTransaction().commit();
		s.close();
	}
