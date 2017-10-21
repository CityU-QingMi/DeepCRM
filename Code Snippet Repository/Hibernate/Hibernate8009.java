	@Test
	public void testDeleteOnDiscriminatorSubclass() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "delete PettingZoo" ).executeUpdate();
		assertEquals( "Incorrect discrim subclass delete count", 1, count );

		count = s.createQuery( "delete Zoo" ).executeUpdate();
		assertEquals( "Incorrect discrim subclass delete count", 1, count );

		t.commit();
		s.close();

		data.cleanup();
	}
