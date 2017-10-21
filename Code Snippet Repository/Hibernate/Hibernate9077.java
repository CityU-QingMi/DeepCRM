	@Test
	public void testSubsequentNonExistentProxyAccess() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		DataPoint proxy = ( DataPoint ) s.load( DataPoint.class, new Long(-1) );
		assertFalse( Hibernate.isInitialized( proxy ) );
		try {
			proxy.getDescription();
			fail( "proxy access did not fail on non-existent proxy" );
		}
		catch( ObjectNotFoundException onfe ) {
			// expected
		}
		catch( Throwable e ) {
			fail( "unexpected exception type on non-existent proxy access : " + e );
		}
		// try it a second (subsequent) time...
		try {
			proxy.getDescription();
			fail( "proxy access did not fail on non-existent proxy" );
		}
		catch( ObjectNotFoundException onfe ) {
			// expected
		}
		catch( Throwable e ) {
			fail( "unexpected exception type on non-existent proxy access : " + e );
		}

		t.commit();
		s.close();
	}
