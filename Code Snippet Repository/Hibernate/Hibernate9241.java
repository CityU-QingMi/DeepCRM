	@Test
	public void testModifiableSessionReadOnlyQueryIterate() {
		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		Transaction t = s.beginTransaction();
		for ( int i=0; i<100; i++ ) {
			DataPoint dp = new DataPoint();
			dp.setX( new BigDecimal(i * 0.1d).setScale(19, BigDecimal.ROUND_DOWN) );
			dp.setY( new BigDecimal( Math.cos( dp.getX().doubleValue() ) ).setScale(19, BigDecimal.ROUND_DOWN) );
			s.save(dp);
		}
		t.commit();
		s.close();

		s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		t = s.beginTransaction();
		assertFalse( s.isDefaultReadOnly() );
		int i = 0;
		Iterator it = s.createQuery("from DataPoint dp order by dp.x asc")
				.setReadOnly( true )
				.iterate();
		while ( it.hasNext() ) {
			DataPoint dp = (DataPoint) it.next();
			if (++i==50) {
				s.setReadOnly(dp, false);
			}
			dp.setDescription("done!");
		}
		t.commit();
		s.clear();
		t = s.beginTransaction();
		List single = s.createQuery("from DataPoint where description='done!'").list();
		assertEquals( 1, single.size() );
		s.createQuery("delete from DataPoint").executeUpdate();
		t.commit();
		s.close();
	}
