	@Test
	public void testUpdateOnDiscriminatorSubclass() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "update PettingZoo set name = name" ).executeUpdate();
		assertEquals( "Incorrect discrim subclass update count", 1, count );

		t.rollback();
		t = s.beginTransaction();

		count = s.createQuery( "update PettingZoo pz set pz.name = pz.name where pz.id = :id" )
				.setLong( "id", data.pettingZoo.getId().longValue() )
				.executeUpdate();
		assertEquals( "Incorrect discrim subclass update count", 1, count );

		t.rollback();
		t = s.beginTransaction();

		count = s.createQuery( "update Zoo as z set z.name = z.name" ).executeUpdate();
		assertEquals( "Incorrect discrim subclass update count", 2, count );

		t.rollback();
		t = s.beginTransaction();

		// TODO : not so sure this should be allowed.  Seems to me that if they specify an alias,
		// property-refs should be required to be qualified.
		count = s.createQuery( "update Zoo as z set name = name where id = :id" )
				.setLong( "id", data.zoo.getId().longValue() )
				.executeUpdate();
		assertEquals( "Incorrect discrim subclass update count", 1, count );

		t.commit();
		s.close();

		data.cleanup();
	}
