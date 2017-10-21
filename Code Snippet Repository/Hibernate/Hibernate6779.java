	@Test
	public void testOrderByContractor() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();

		// create some test entities
		Employer employer = new Employer();
		Contractor contractor1 = new Contractor();
		contractor1.setName( "Emmanuel" );
		contractor1.setHourlyRate(100.0f);
		Contractor contractor2 = new Contractor();
		contractor2.setName( "Hardy" );
		contractor2.setHourlyRate(99.99f);
		s.persist( contractor1 );
		s.persist( contractor2 );

		// add contractors to employer
		List setOfContractors = new ArrayList();
		setOfContractors.add( contractor1 );
		setOfContractors.add( contractor2 );
		employer.setContractors( setOfContractors );

		// add employer to contractors
		Collection employerListContractor1 = new ArrayList();
		employerListContractor1.add( employer );
		contractor1.setEmployers( employerListContractor1 );

		Collection employerListContractor2 = new ArrayList();
		employerListContractor2.add( employer );
		contractor2.setEmployers( employerListContractor2 );

		s.flush();
		s.clear();

		// assertions
		employer = (Employer) s.get( Employer.class, employer.getId() );
		assertNotNull( employer );
		assertNotNull( employer.getContractors() );
		assertEquals( 2, employer.getContractors().size() );
		Contractor firstContractorFromDb = (Contractor) employer.getContractors().iterator().next();
		assertEquals( contractor2.getName(), firstContractorFromDb.getName() );
		tx.rollback();
		s.close();
	}
