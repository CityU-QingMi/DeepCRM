	@Test
	public void testReadOnlyModeAutoFlushOnQuery() {
		clearCounts();

		Session s = openSession();
		Transaction t = s.beginTransaction();
		DataPoint dpFirst = null;
		for ( int i=0; i<100; i++ ) {
			DataPoint dp = new DataPoint();
			dp.setX( new BigDecimal(i * 0.1d).setScale(19, BigDecimal.ROUND_DOWN) );
			dp.setY( new BigDecimal( Math.cos( dp.getX().doubleValue() ) ).setScale(19, BigDecimal.ROUND_DOWN) );
			s.save(dp);
		}

		assertInsertCount( 0 );
		assertUpdateCount( 0 );

		ScrollableResults sr = s.createQuery("from DataPoint dp order by dp.x asc")
				.setReadOnly(true)
				.scroll(ScrollMode.FORWARD_ONLY);

		assertInsertCount( 100 );
		assertUpdateCount( 0 );
		clearCounts();

		while ( sr.next() ) {
			DataPoint dp = (DataPoint) sr.get(0);
			assertFalse( s.isReadOnly( dp ) );
			s.delete( dp );
		}
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 100 );
	}
