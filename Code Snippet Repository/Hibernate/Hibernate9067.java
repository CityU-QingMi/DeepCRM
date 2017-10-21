	@Test
	public void testFinalizeFiltered() {
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
			dp.getClass().getDeclaredMethod( "finalize", (Class[]) null );
			fail();

		}
		catch (NoSuchMethodException e) {}

		s.delete(dp);
		t.commit();
		s.close();

	}
