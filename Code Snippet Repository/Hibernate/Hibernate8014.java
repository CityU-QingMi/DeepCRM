	@Test
	public void testDeleteUnionSubclassConcreteSubclass() {
		TestData data = new TestData();
		data.prepare();

		// These should only affect the given table
		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "delete Truck where owner = :owner" ).setString( "owner", "Steve" ).executeUpdate();
		assertEquals( "incorrect restricted update count", 1, count );

		count = s.createQuery( "delete Truck" ).executeUpdate();
		assertEquals( "incorrect update count", 2, count );
		t.commit();
		s.close();

		data.cleanup();
	}
