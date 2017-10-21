	@Test
	public void testInitializedProxySerializationAfterSessionClosed() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		DataPoint dp = new DataPoint();
		dp.setDescription("a data point");
		dp.setX( new BigDecimal(1.0) );
		dp.setY( new BigDecimal(2.0) );
		s.persist(dp);
		s.flush();
		s.clear();

		dp = (DataPoint) s.load( DataPoint.class, new Long( dp.getId() ) );
		assertFalse( Hibernate.isInitialized(dp) );
		Hibernate.initialize( dp );
		assertTrue( Hibernate.isInitialized(dp) );
		s.close();
		SerializationHelper.clone( dp );

		s = openSession();
		t = s.beginTransaction();
		s.delete( dp );
		t.commit();
		s.close();
	}
