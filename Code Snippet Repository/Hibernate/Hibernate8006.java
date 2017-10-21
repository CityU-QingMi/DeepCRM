	@Test
	public void testUpdateSetNullOnJoinedSubclass() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "update Mammal set bodyWeight = null" ).executeUpdate();
		assertEquals( "Incorrect deletion count on joined subclass", 2, count );

		count = s.createQuery( "delete Animal where bodyWeight = null" ).executeUpdate();
		assertEquals( "Incorrect deletion count on joined subclass", 2, count );

		t.commit();
		s.close();

		data.cleanup();
	}
