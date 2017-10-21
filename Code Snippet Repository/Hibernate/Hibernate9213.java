	@Test
	public void testDetachedSetReadOnlyAfterEvictViaLazyInitializer() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);

		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		assertFalse( Hibernate.isInitialized( dp ) );
		checkReadOnly( s, dp, false );
		s.evict( dp );
		assertFalse( s.contains( dp ) );
		assertNull( ( ( HibernateProxy ) dp ).getHibernateLazyInitializer().getSession() );
		try {
 			( ( HibernateProxy ) dp ).getHibernateLazyInitializer().setReadOnly( true );
			fail( "should have failed because proxy was detached" );
		}
		catch ( TransientObjectException ex) {
			// expected
			assertFalse( ( ( HibernateProxy ) dp ).getHibernateLazyInitializer().isReadOnlySettingAvailable() );
		}
		finally {
			s.delete( dp );
			s.getTransaction().commit();
			s.close();
		}
	}
