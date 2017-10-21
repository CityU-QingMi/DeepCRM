	@Test
	public void testIncrementTimestampVersion() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		TimestampVersioned entity = new TimestampVersioned( "ts-vers" );
		s.save( entity );
		t.commit();
		s.close();

		Date initialVersion = entity.getVersion();

		synchronized (this) {
			try {
				wait(1500);
			}
			catch (InterruptedException ie) {}
		}

		s = openSession();
		t = s.beginTransaction();
		int count = s.createQuery( "update versioned TimestampVersioned set name = name" ).executeUpdate();
		assertEquals( "incorrect exec count", 1, count );
		t.commit();

		t = s.beginTransaction();
		entity = ( TimestampVersioned ) s.load( TimestampVersioned.class, entity.getId() );
		assertTrue( "version not incremented", entity.getVersion().after( initialVersion ) );

		s.delete( entity );
		t.commit();
		s.close();
	}
