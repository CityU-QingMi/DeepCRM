	@Test
	public void testSaveOrUpdateNullCollection() {
		Session s = openSession();
		s.getTransaction().begin();
		VersionedNoCascadeOne one = new VersionedNoCascadeOne();
		assertNull( one.getManies() );
		s.save( one );
		assertNull( one.getManies() );
		EntityEntry eeOne = getEntityEntry( s, one  );
		assertNull( eeOne.getLoadedValue( "manies" ) );
		s.flush();
		assertNull( one.getManies() );
		assertNull( eeOne.getLoadedValue( "manies" ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		s.saveOrUpdate( one );

		// Ensure one.getManies() is still null.
		assertNull( one.getManies() );

		// Ensure the EntityEntry loaded state contains null for the manies collection.
		eeOne = getEntityEntry( s, one  );
		assertNull( eeOne.getLoadedValue( "manies" ) );

		s.flush();

		// Ensure one.getManies() is still null.
		assertNull( one.getManies() );

		// Ensure the same EntityEntry is being used.
		assertSame( eeOne, getEntityEntry( s, one ) );

		// Ensure the EntityEntry loaded state still contains null for the manies collection.
		assertNull( eeOne.getLoadedValue( "manies" ) );

		s.getTransaction().commit();
		s.close();
	}
