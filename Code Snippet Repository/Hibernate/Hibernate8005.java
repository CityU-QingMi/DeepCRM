	@Test
	public void testUpdateSetNullOnDiscriminatorSubclass() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "update PettingZoo set address.city = null" ).executeUpdate();
		assertEquals( "Incorrect discrim subclass delete count", 1, count );
		count = s.createQuery( "delete Zoo where address.city is null" ).executeUpdate();
		assertEquals( "Incorrect discrim subclass delete count", 1, count );

		count = s.createQuery( "update Zoo set address.city = null" ).executeUpdate();
		assertEquals( "Incorrect discrim subclass delete count", 1, count );
		count = s.createQuery( "delete Zoo where address.city is null" ).executeUpdate();
		assertEquals( "Incorrect discrim subclass delete count", 1, count );

		t.commit();
		s.close();

		data.cleanup();
	}
