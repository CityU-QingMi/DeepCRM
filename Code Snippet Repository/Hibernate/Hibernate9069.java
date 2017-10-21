	private static DataPoint newPersistentDataPoint(Session s) {
		DataPoint dp = new DataPoint();
		dp.setDescription( "a data point" );
		dp.setX( new BigDecimal( 1.0 ) );
		dp.setY( new BigDecimal( 2.0 ) );
		s.persist( dp );
		s.flush();
		s.clear();
		return dp;
	}
