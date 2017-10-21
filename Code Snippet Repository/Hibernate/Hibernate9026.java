	private void prepareTestData() {
		Session session = openSession();
		session.beginTransaction();
		for ( int i = 0; i < NUMBER_OF_TEST_ROWS; i++ ) {
			DataPoint dataPoint = new DataPoint();
			dataPoint.setSequence( i );
			dataPoint.setDescription( "data point #" + i );
			BigDecimal x = new BigDecimal( i * 0.1d ).setScale( 19, BigDecimal.ROUND_DOWN );
			dataPoint.setX( x );
			dataPoint.setY( new BigDecimal( Math.cos( x.doubleValue() ) ).setScale( 19, BigDecimal.ROUND_DOWN ) );
			dataPoint.setDescription( "Description: " + i % 5 );
			session.save( dataPoint );
		}
		session.getTransaction().commit();
		session.close();
	}
