	@Test
	public void testSetReadOnlyAfterSessionClosed() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);

		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		assertFalse( Hibernate.isInitialized( dp ) );
		checkReadOnly( s, dp, false );
		s.getTransaction().commit();
		s.close();

		try {
 			s.setReadOnly( dp, true );
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
