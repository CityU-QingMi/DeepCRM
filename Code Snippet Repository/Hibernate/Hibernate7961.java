	@Test
	public void testFromOnly() throws Exception {
		createTestBaseData();
		Session session = openSession();
		Transaction t = session.beginTransaction();
		List results = session.createQuery( "from Animal" ).list();
		assertEquals( "Incorrect result size", 2, results.size() );
		assertTrue( "Incorrect result return type", results.get( 0 ) instanceof Animal );
		t.commit();
		session.close();
		destroyTestBaseData();
	}
