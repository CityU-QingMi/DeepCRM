	@Test
	public void testReadOnlyViaSessionBeforeInit() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		s.setReadOnly( dp, true );
		dp.setDescription( "changed" );
		assertTrue( Hibernate.isInitialized( dp ) );
		assertEquals( "changed", dp.getDescription() );
		checkReadOnly( s, dp, true );
		s.flush();
		s.getTransaction().commit();
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
