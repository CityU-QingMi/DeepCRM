	@Test
	public void testModifiableModeWithExistingReadOnlyEntity() {
		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		Transaction t = s.beginTransaction();
		DataPoint dp = null;
		for ( int i=0; i<100; i++ ) {
			dp = new DataPoint();
			dp.setX( new BigDecimal(i * 0.1d).setScale(19, BigDecimal.ROUND_DOWN) );
			dp.setY( new BigDecimal( Math.cos( dp.getX().doubleValue() ) ).setScale(19, BigDecimal.ROUND_DOWN) );
			s.save(dp);
		}
		t.commit();
		s.close();

		s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		t = s.beginTransaction();
		s.setDefaultReadOnly( true );
		DataPoint dpLast = ( DataPoint ) s.get( DataPoint.class,  dp.getId() );
		assertTrue( s.isReadOnly( dpLast ) );
		int i = 0;
		ScrollableResults sr = s.createQuery("from DataPoint dp order by dp.x asc")
				.setReadOnly(false)
				.scroll(ScrollMode.FORWARD_ONLY);
		int nExpectedChanges = 0;
		while ( sr.next() ) {
			dp = (DataPoint) sr.get(0);
			if ( dp.getId() == dpLast.getId() ) {
				//dpLast existed in the session before executing the read-only query
				assertTrue( s.isReadOnly( dp ) );
			}
			else {
				assertFalse( s.isReadOnly( dp ) );
			}
			if (++i==50) {
				s.setReadOnly(dp, true);
				nExpectedChanges = ( dp == dpLast ? 99 : 98 );
			}
			dp.setDescription("done!");
		}
		t.commit();
		s.clear();
		t = s.beginTransaction();
		List list = s.createQuery("from DataPoint where description='done!'").list();
		assertEquals( nExpectedChanges, list.size() );
		s.createQuery("delete from DataPoint").executeUpdate();
		t.commit();
		s.close();
	}
