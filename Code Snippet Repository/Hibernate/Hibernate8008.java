	public void testSimpleDeleteOnAnimal() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "delete from Animal as a where a.id = :id" )
				.setLong( "id", data.polliwog.getId().longValue() )
				.executeUpdate();
		assertEquals( "Incorrect delete count", 1, count );

		count = s.createQuery( "delete Animal where id = :id" )
				.setLong( "id", data.catepillar.getId().longValue() )
				.executeUpdate();
		assertEquals( "incorrect delete count", 1, count );

		if ( getDialect().supportsSubqueryOnMutatingTable() ) {
			count = s.createQuery( "delete from User u where u not in (select u from User u)" ).executeUpdate();
			assertEquals( 0, count );
		}

		count = s.createQuery( "delete Animal a" ).executeUpdate();
		assertEquals( "Incorrect delete count", 4, count );

		List list = s.createQuery( "select a from Animal as a" ).list();
		assertTrue( "table not empty", list.isEmpty() );

		t.commit();
		s.close();
		data.cleanup();
	}
