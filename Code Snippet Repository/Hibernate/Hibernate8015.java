	@Test
	public void testDeleteUnionSubclassLeafSubclass() {
		TestData data = new TestData();
		data.prepare();

		// These should only affect the given table
		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "delete Car where owner = :owner" ).setString( "owner", "Kirsten" ).executeUpdate();
		assertEquals( "incorrect restricted update count", 1, count );

		count = s.createQuery( "delete Car" ).executeUpdate();
		assertEquals( "incorrect update count", 0, count );
		t.commit();
		s.close();

		data.cleanup();
	}
