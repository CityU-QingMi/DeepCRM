	@Test
	public void testProxySerialization() {
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
		dp.getId();
		assertFalse( Hibernate.isInitialized(dp) );
		dp.getDescription();
		assertTrue( Hibernate.isInitialized(dp) );
		Object none = s.load( DataPoint.class, new Long(666));
		assertFalse( Hibernate.isInitialized(none) );

		t.commit();
		s.disconnect();

		Object[] holder = new Object[] { s, dp, none };

		holder = (Object[]) SerializationHelper.clone(holder);
		Session sclone = (Session) holder[0];
		dp = (DataPoint) holder[1];
		none = holder[2];

		//close the original:
		s.close();

		t = sclone.beginTransaction();

		DataPoint sdp = (DataPoint) sclone.load( DataPoint.class, new Long( dp.getId() ) );
		assertSame(dp, sdp);
		assertFalse(sdp instanceof HibernateProxy);
		Object snone = sclone.load( DataPoint.class, new Long(666) );
		assertSame(none, snone);
		assertTrue(snone instanceof HibernateProxy);

		sclone.delete(dp);

		t.commit();
		sclone.close();

	}
