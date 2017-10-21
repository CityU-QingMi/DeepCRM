	@Test
	public void testDeleteOnJoinedSubclass() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "delete Mammal where bodyWeight > 150" ).executeUpdate();
		assertEquals( "Incorrect deletion count on joined subclass", 1, count );

		count = s.createQuery( "delete Mammal" ).executeUpdate();
		assertEquals( "Incorrect deletion count on joined subclass", 1, count );

		count = s.createQuery( "delete SubMulti" ).executeUpdate();
		assertEquals( "Incorrect deletion count on joined subclass", 0, count );

		t.commit();
		s.close();

		data.cleanup();
	}
