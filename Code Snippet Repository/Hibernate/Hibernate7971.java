	@Test
	public void testProjectionQueries() throws Exception {
		createTestBaseData();
		Session session = openSession();
		Transaction t = session.beginTransaction();

		List results = session.createQuery( "select an.mother.id, max(an.bodyWeight) from Animal an group by an.mother.id" ).list();
		// mysql returns nulls in this group by
		assertEquals( "Incorrect result size", 2, results.size() );
		assertTrue( "Incorrect return type", results.get( 0 ) instanceof Object[] );
		assertEquals( "Incorrect return dimensions", 2, ((Object[]) results.get( 0 )).length );

		t.commit();
		session.close();
		destroyTestBaseData();
	}
