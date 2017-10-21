	public void testLimit() {
		prepareTestData();

		Session session = openSession();
		session.beginTransaction();

		int count;

		count = generateBaseHQLQuery( session )
				.setMaxResults( 5 )
				.list()
				.size();
		assertEquals( 5, count );

		count = generateBaseCriteria( session )
				.setMaxResults( 18 )
				.list()
				.size();
		assertEquals( 18, count );

		count = generateBaseSQLQuery( session )
				.setMaxResults( 13 )
				.list()
				.size();
		assertEquals( 13, count );

		session.getTransaction().commit();
		session.close();

		cleanupTestData();
	}
