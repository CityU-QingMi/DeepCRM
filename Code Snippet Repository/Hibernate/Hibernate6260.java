	@Test
	public void testNonGetter() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Flight airFrance = new Flight();
		airFrance.setId( Long.valueOf( 747 ) );
		airFrance.setName( "Paris-Amsterdam" );
		airFrance.setDuration( Long.valueOf( 10 ) );
		airFrance.setFactor( 25 );
		s.persist( airFrance );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		airFrance = (Flight) s.get( Flight.class, airFrance.getId() );
		assertNotNull( airFrance );
		assertEquals( Long.valueOf( 10 ), airFrance.getDuration() );
		assertFalse( 25 == airFrance.getFactor( false ) );
		s.delete( airFrance );
		tx.commit();
		s.close();
	}
