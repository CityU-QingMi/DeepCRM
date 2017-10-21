	@Test
	public void testCompleteScalarAutoDiscovery() {
		prepareTestData();

		Session s = openSession();
		s.beginTransaction();
		s.createSQLQuery( queryString )
				.list();
		s.getTransaction().commit();
		s.close();
	}
