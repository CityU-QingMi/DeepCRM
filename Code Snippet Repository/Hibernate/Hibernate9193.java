	@Test
	public void testReadOnlyViaLazyInitializerDoesNotInit() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		LazyInitializer dpLI = ( ( HibernateProxy ) dp ).getHibernateLazyInitializer();
		checkReadOnly( s, dp, false );
		assertFalse( Hibernate.isInitialized( dp ) );
		dpLI.setReadOnly( true );
		checkReadOnly( s, dp, true );
		assertFalse( Hibernate.isInitialized( dp ) );
		dpLI.setReadOnly( false );
		checkReadOnly( s, dp, false );
		assertFalse( Hibernate.isInitialized( dp ) );
		s.flush();
		checkReadOnly( s, dp, false );
		assertFalse( Hibernate.isInitialized( dp ) );
		s.getTransaction().commit();
		checkReadOnly( s, dp, false );
		assertFalse( Hibernate.isInitialized( dp ) );
		s.close();

		s = openSession();
		s.beginTransaction();
		dp = ( DataPoint ) s.get( DataPoint.class, dpOrig.getId() );
		assertEquals( dpOrig.getId(), dp.getId() );
		assertEquals( dpOrig.getDescription(), dp.getDescription() );
		assertEquals( dpOrig.getX(), dp.getX() );
		assertEquals( dpOrig.getY(), dp.getY() );
		s.delete( dp );
		s.getTransaction().commit();
		s.close();
	}
