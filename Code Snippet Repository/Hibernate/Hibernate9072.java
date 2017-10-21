	@Test
	public void testProxyException() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		DataPoint dp = new DataPoint();
		dp.setDescription("a data point");
		dp.setX( new BigDecimal(1.0) );
		dp.setY( new BigDecimal(2.0) );
		s.persist(dp);
		s.flush();
		s.clear();

		dp = (DataPoint) s.load(DataPoint.class, new Long( dp.getId() ) );
		assertFalse( Hibernate.isInitialized(dp) );

		try {
			dp.exception();
			fail();
		}
		catch (Exception e) {
			assertTrue( e.getClass()==Exception.class );
		}
		s.delete(dp);
		t.commit();
		s.close();
	}
