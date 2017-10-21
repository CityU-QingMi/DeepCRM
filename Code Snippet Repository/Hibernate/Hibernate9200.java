	@Test
	public void testReadOnlyEntityMergeDetachedProxyWithChange() {
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
		DataPoint dpEntity = ( DataPoint ) s.get( DataPoint.class, new Long( dpOrig.getId() ) );
		assertFalse( dpEntity instanceof HibernateProxy );
		assertFalse( s.isReadOnly( dpEntity ) );
		s.setReadOnly( dpEntity, true );
		assertTrue( s.isReadOnly( dpEntity ) );
		DataPoint dpMerged = ( DataPoint ) s.merge( dp );
		assertSame( dpEntity, dpMerged );
		assertEquals( "changed", dpEntity.getDescription() );
		assertTrue( s.isReadOnly( dpEntity ) );
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
