	@Test
	public void testCreate() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Flight firstOne = new Flight();
		firstOne.setId( new Long( 1 ) );
		firstOne.setName( "AF0101" );
		firstOne.setDuration( new Long( 1000 ) );
		Company frenchOne = new Company();
		frenchOne.setName( "Air France" );
		firstOne.setCompany( frenchOne );
		s.persist( firstOne );
		tx.commit();
		s.close();
		assertNotNull( "identity id should work", frenchOne.getId() );

		s = openSession();
		tx = s.beginTransaction();
		firstOne = (Flight) s.get( Flight.class, new Long( 1 ) );
		assertNotNull( firstOne.getCompany() );
		assertEquals( frenchOne.getName(), firstOne.getCompany().getName() );
		tx.commit();
		s.close();
	}
