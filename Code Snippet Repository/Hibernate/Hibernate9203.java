	@Test
	public void testSetModifiableBetweenTwoTransactionsSameSession() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);

		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		assertFalse( Hibernate.isInitialized( dp ) );
		checkReadOnly( s, dp, false );
		s.setReadOnly( dp, true );
		checkReadOnly( s, dp, true );
		dp.setDescription( "changed" );
		assertTrue( Hibernate.isInitialized( dp ) );
		assertEquals( "changed", dp.getDescription() );
		checkReadOnly( s, dp, true );
		s.flush();
		s.getTransaction().commit();

		checkReadOnly( s, dp, true );
		s.setReadOnly( dp, false );
		checkReadOnly( s, dp, false );

		s.beginTransaction();
		checkReadOnly( s, dp, false );
		assertEquals( "changed", dp.getDescription() );
		s.refresh( dp );
		assertEquals( dpOrig.getDescription(), dp.getDescription() );
		checkReadOnly( s, dp, false );
		dp.setDescription( "changed again" );
		assertEquals( "changed again", dp.getDescription() );
		s.flush();
		s.getTransaction().commit();

		s.close();

		s = openSession();
		s.beginTransaction();
		dp = ( DataPoint ) s.get( DataPoint.class, dpOrig.getId() );
		assertEquals( dpOrig.getId(), dp.getId() );
		assertEquals( "changed again", dp.getDescription() );
		assertEquals( dpOrig.getX(), dp.getX() );
		assertEquals( dpOrig.getY(), dp.getY() );
		s.delete( dp );
		s.getTransaction().commit();
		s.close();
	}
