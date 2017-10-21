	@Test
	public void testReadOnlyDelete() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		checkReadOnly( s, dp, false );
		s.setReadOnly( dp, true );
		checkReadOnly( s, dp,true );
		assertFalse( Hibernate.isInitialized( dp ));
		s.delete( dp );
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		dp = ( DataPoint ) s.get( DataPoint.class, dpOrig.getId() );
		assertNull( dp );
		s.getTransaction().commit();
		s.close();
	}
