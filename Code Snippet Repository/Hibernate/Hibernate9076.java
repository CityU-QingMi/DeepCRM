	@Test
	public void testProxy() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		DataPoint dp = new DataPoint();
		dp.setDescription("a data point");
		dp.setX( new BigDecimal(1.0) );
		dp.setY( new BigDecimal(2.0) );
		s.persist(dp);
		s.flush();
		s.clear();

		dp = (DataPoint) s.load( DataPoint.class, new Long(dp.getId() ));
		assertFalse( Hibernate.isInitialized(dp) );
		DataPoint dp2 = (DataPoint) s.get( DataPoint.class, new Long(dp.getId()) );
		assertSame(dp, dp2);
		assertTrue( Hibernate.isInitialized(dp) );
		s.clear();

		dp = (DataPoint) s.load( DataPoint.class, new Long( dp.getId() ) );
		assertFalse( Hibernate.isInitialized(dp) );
		dp2 = (DataPoint) s.load( DataPoint.class, new Long( dp.getId() ), LockMode.NONE );
		assertSame(dp, dp2);
		assertFalse( Hibernate.isInitialized(dp) );
		s.clear();

		dp = (DataPoint) s.load( DataPoint.class, new Long( dp.getId() ) );
		assertFalse( Hibernate.isInitialized(dp) );
		dp2 = (DataPoint) s.load( DataPoint.class, new Long( dp.getId() ), LockMode.READ );
		assertSame(dp, dp2);
		assertTrue( Hibernate.isInitialized(dp) );
		s.clear();

		dp = (DataPoint) s.load( DataPoint.class, new Long (dp.getId() ));
		assertFalse( Hibernate.isInitialized(dp) );
		dp2 = (DataPoint) s.byId( DataPoint.class ).with( LockOptions.READ ).load( dp.getId() );
		assertSame(dp, dp2);
		assertTrue( Hibernate.isInitialized(dp) );
		s.clear();

		dp = (DataPoint) s.load( DataPoint.class, new Long  ( dp.getId() ) );
		assertFalse( Hibernate.isInitialized(dp) );
		dp2 = (DataPoint) s.createQuery("from DataPoint").uniqueResult();
		assertSame(dp, dp2);
		assertTrue( Hibernate.isInitialized(dp) );
		s.delete( dp );
		t.commit();
		s.close();
	}
