	@Test
	public void testReadOnlyProxyInitMergeDetachedProxyWithChange() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		checkReadOnly( s, dp, false );
		assertFalse( Hibernate.isInitialized( dp ));
		Hibernate.initialize( dp );
		assertTrue( Hibernate.isInitialized( dp ) );
		s.getTransaction().commit();
		s.close();

		// modify detached proxy
		dp.setDescription( "changed" );

		s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		s.beginTransaction();
		DataPoint dpLoaded = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dpLoaded instanceof HibernateProxy );
		assertFalse( Hibernate.isInitialized( dpLoaded ) );
		Hibernate.initialize( dpLoaded );
		assertTrue( Hibernate.isInitialized( dpLoaded ) );
		checkReadOnly( s, dpLoaded, false );
		s.setReadOnly( dpLoaded, true );
		checkReadOnly( s, dpLoaded,true );
		DataPoint dpMerged = ( DataPoint ) s.merge( dp );
		assertSame( dpLoaded, dpMerged );
		assertEquals( "changed", dpLoaded.getDescription() );
		checkReadOnly( s, dpLoaded, true );
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
