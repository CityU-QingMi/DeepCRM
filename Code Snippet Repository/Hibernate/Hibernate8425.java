	@Test
	public void testGetSemantics() {
		Long nonExistentId = new Long( -1 );
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		Item item = ( Item ) s.get( Item.class, nonExistentId );
		assertNull( "get() of non-existent entity did not return null", item );
		txn.commit();
		s.close();

		s = openSession();
		txn = s.beginTransaction();
		// first load() it to generate a proxy...
		item = ( Item ) s.load( Item.class, nonExistentId );
		assertFalse( Hibernate.isInitialized( item ) );
		// then try to get() it to make sure we get an exception
		try {
			s.get( Item.class, nonExistentId );
			fail( "force load did not fail on non-existent entity" );
		}
		catch ( EntityNotFoundException e ) {
			// expected behavior
		}
		catch( AssertionFailedError e ) {
			throw e;
		}
		catch ( Throwable t ) {
			fail( "unexpected exception type on non-existent entity force load : " + t );
		}
		txn.commit();
		s.close();
	}
