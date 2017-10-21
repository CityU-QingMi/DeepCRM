	@Test
	public void testSetClosedSessionInLazyInitializer() {
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
		assertTrue( ( (SessionImplementor) s ).isClosed() );
		try {
			( ( HibernateProxy ) dp ).getHibernateLazyInitializer().setSession( ( SessionImplementor ) s );			
			fail( "should have failed because session was closed" );
		}
		catch ( IllegalStateException ex) {
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
