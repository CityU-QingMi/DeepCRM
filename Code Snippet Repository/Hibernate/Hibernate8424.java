	@Test
	public void testEjb3ProxyUsage() {
		Session s = openSession();
		Transaction txn = s.beginTransaction();

		Item item = ( Item ) s.load( Item.class, new Long(-1) );
		assertFalse( Hibernate.isInitialized( item ) );
		try {
			Hibernate.initialize( item );
			fail( "proxy access did not fail on non-existent proxy" );
		}
		catch ( EntityNotFoundException e ) {
			// expected behavior
		}
		catch ( Throwable t ) {
			fail( "unexpected exception type on non-existent proxy access : " + t );
		}

		s.clear();

		Item item2 = ( Item ) s.load( Item.class, new Long(-1) );
		assertFalse( Hibernate.isInitialized( item2 ) );
		assertFalse( item == item2 );
		try {
			item2.getName();
			fail( "proxy access did not fail on non-existent proxy" );
		}
		catch ( EntityNotFoundException e ) {
			// expected behavior
		}
		catch ( Throwable t ) {
			fail( "unexpected exception type on non-existent proxy access : " + t );
		}

		txn.commit();
		s.close();
	}
