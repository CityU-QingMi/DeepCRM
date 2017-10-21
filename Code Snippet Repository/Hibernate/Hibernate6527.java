	@Test
	public void testFormula() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		org.hibernate.test.annotations.entity.Flight airFrance = new Flight();
		airFrance.setId( new Long( 747 ) );
		airFrance.setMaxAltitude( 10000 );
		s.persist( airFrance );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		airFrance = (Flight) s.get( Flight.class, airFrance.getId() );
		assertNotNull( airFrance );
		assertEquals( 10000000, airFrance.getMaxAltitudeInMilimeter() );
		s.delete( airFrance );
		tx.commit();
		s.close();
	}
