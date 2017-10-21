	@Test
	public void testModifiableSessionDefaultQueryReadOnlySessionScroll() {
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
		s.setDefaultReadOnly( false );
		int i = 0;
		Query query = s.createQuery("from DataPoint dp order by dp.x asc");
		s.setDefaultReadOnly( true );
		ScrollableResults sr = query.scroll(ScrollMode.FORWARD_ONLY);
		s.setDefaultReadOnly( false );
		while ( sr.next() ) {
			DataPoint dp = (DataPoint) sr.get(0);
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
