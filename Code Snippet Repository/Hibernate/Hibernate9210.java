	@Test
	public void testSetReadOnlyAfterSessionClosedViaLazyInitializer() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);

		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		assertFalse( Hibernate.isInitialized( dp ) );
		checkReadOnly( s, dp, false );
		s.getTransaction().commit();
		assertTrue( s.contains( dp ) );
		s.close();

		assertNull( ( ( HibernateProxy ) dp ).getHibernateLazyInitializer().getSession() );
		try {
 			( ( HibernateProxy ) dp ).getHibernateLazyInitializer().setReadOnly( true );
			fail( "should have failed because session was detached" );
		}
		catch ( TransientObjectException ex) {
			// expected
			assertFalse( ( ( HibernateProxy ) dp ).getHibernateLazyInitializer().isReadOnlySettingAvailable() );
		}
		finally {
			s = openSession();
			s.beginTransaction();
			s.delete( dp );
			s.getTransaction().commit();
			s.close();
		}
	}
