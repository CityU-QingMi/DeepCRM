	@Test
	public void testLoad() throws Exception {
		//put an object in DB
		assertEquals( "Flight", metadata().getEntityBinding( Flight.class.getName() ).getTable().getName() );

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Flight firstOne = new Flight();
		firstOne.setId( Long.valueOf( 1 ) );
		firstOne.setName( "AF3202" );
		firstOne.setDuration( new Long( 1000000 ) );
		firstOne.setDurationInSec( 2000 );
		s.save( firstOne );
		s.flush();
		tx.commit();
		s.close();

		//read it
		s = openSession();
		tx = s.beginTransaction();
		firstOne = (Flight) s.get( Flight.class, Long.valueOf( 1 ) );
		assertNotNull( firstOne );
		assertEquals( Long.valueOf( 1 ), firstOne.getId() );
		assertEquals( "AF3202", firstOne.getName() );
		assertEquals( Long.valueOf( 1000000 ), firstOne.getDuration() );
		assertFalse( "Transient is not working", 2000l == firstOne.getDurationInSec() );
		tx.commit();
		s.close();
	}
