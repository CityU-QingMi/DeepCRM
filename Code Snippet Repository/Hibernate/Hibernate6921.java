	@Test
	@TestForIssue(jiraKey = "")
	public void testNonPkInheritedFk() throws Exception {
		MarketRegion region1 = new MarketRegion();
		region1.setRegionCode( "US" );
		MarketRegion region2 = new MarketRegion();
		region2.setRegionCode( "EU" );
		
		Car car = new Car();
		car.setModel( "SUV" );
		car.setMarketRegion( region1 );
		
		SportCar car2 = new SportCar();
		car2.setModel( "350Z" );
		car2.setMarketRegion( region2 );
		
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( region1 );
		s.persist( region2 );
		s.persist( car );
		s.persist( car2 );
		
		s.flush();
		
		assertEquals( 1, s.createQuery( "select car from Car car where car.marketRegion.regionCode='US'")
				.list().size() );
		assertEquals( 1, s.createQuery( "select car from SportCar car where car.marketRegion.regionCode='EU'")
				.list().size() );
		
		tx.rollback();
		s.close();

	}
