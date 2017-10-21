	@Test
	public void testIncrementCounterVersion() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		IntegerVersioned entity = new IntegerVersioned( "int-vers" );
		s.save( entity );
		t.commit();
		s.close();

		int initialVersion = entity.getVersion();

		s = openSession();
		t = s.beginTransaction();
		int count = s.createQuery( "update versioned IntegerVersioned set name = name" ).executeUpdate();
		assertEquals( "incorrect exec count", 1, count );
		t.commit();

		t = s.beginTransaction();
		entity = ( IntegerVersioned ) s.load( IntegerVersioned.class, entity.getId() );
		assertEquals( "version not incremented", initialVersion + 1, entity.getVersion() );

		s.delete( entity );
		t.commit();
		s.close();
	}
