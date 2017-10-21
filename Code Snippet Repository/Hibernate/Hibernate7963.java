	@Test
	public void testEntityPropertySelect() throws Exception {
		createTestBaseData();
		Session session = openSession();
		Transaction t = session.beginTransaction();
		List results = session.createQuery( "select a.mother from Animal as a" ).list();
		assertTrue( "Incorrect result return type", results.get( 0 ) instanceof Animal );
		t.commit();
		session.close();
		destroyTestBaseData();
	}
