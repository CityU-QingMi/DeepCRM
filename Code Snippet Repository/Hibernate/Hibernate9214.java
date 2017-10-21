	private DataPoint createDataPoint(CacheMode cacheMode) {
		Session s = openSession();
		s.setCacheMode( cacheMode );
		s.beginTransaction();
		DataPoint dp = new DataPoint();
		dp.setX( new BigDecimal( 0.1d ).setScale(19, BigDecimal.ROUND_DOWN) );
		dp.setY( new BigDecimal( Math.cos( dp.getX().doubleValue() ) ).setScale(19, BigDecimal.ROUND_DOWN) );
		dp.setDescription( "original" );
		s.save( dp );
		s.getTransaction().commit();
		s.close();
		return dp;
	}
