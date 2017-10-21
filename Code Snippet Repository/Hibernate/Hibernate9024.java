	@Test
	public void testOffset() {
		prepareTestData();
		Session session = openSession();
		session.beginTransaction();
		List result;

		result = generateBaseHQLQuery( session )
				.setFirstResult( 3 )
				.list();
		DataPoint firstDataPointHQL = (DataPoint) result.get( 0 );

		result = generateBaseCriteria( session )
				.setFirstResult( 3 )
				.list();
		DataPoint firstDataPointCriteria = (DataPoint) result.get( 0 );

		assertEquals( "The first entry should be the same in HQL and Criteria", firstDataPointHQL, firstDataPointHQL );
		assertEquals( "Wrong first result", 3, firstDataPointCriteria.getSequence() );

		session.getTransaction().commit();
		session.close();
		cleanupTestData();
	}
