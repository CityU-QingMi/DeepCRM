	@Test
	public void testModifiableViaLazyInitializerAfterInit() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		LazyInitializer dpLI = ( ( HibernateProxy ) dp ).getHibernateLazyInitializer();
		assertTrue( dpLI.isUninitialized() );
		checkReadOnly( s, dp, false );
		dp.setDescription( "changed" );
		assertFalse( dpLI.isUninitialized() );
		assertEquals( "changed", dp.getDescription() );
		checkReadOnly( s, dp, false );
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		dp = ( DataPoint ) s.get( DataPoint.class, dpOrig.getId() );
		assertEquals( dpOrig.getId(), dp.getId() );
		assertEquals( "changed", dp.getDescription() );
		assertEquals( dpOrig.getX(), dp.getX() );
		assertEquals( dpOrig.getY(), dp.getY() );
		s.delete( dp );
		s.getTransaction().commit();
		s.close();
	}
