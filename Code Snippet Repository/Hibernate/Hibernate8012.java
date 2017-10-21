	@Test
	public void testDeleteUnionSubclassAbstractRoot() {
		TestData data = new TestData();
		data.prepare();

		// These should reach out into *all* subclass tables...
		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "delete Vehicle where owner = :owner" ).setString( "owner", "Steve" ).executeUpdate();
		assertEquals( "incorrect restricted update count", 1, count );

		count = s.createQuery( "delete Vehicle" ).executeUpdate();
		assertEquals( "incorrect update count", 3, count );
		t.commit();
		s.close();

		data.cleanup();
	}
