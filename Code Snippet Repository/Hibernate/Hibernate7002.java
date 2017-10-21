	@Test
	public void testIndexAndJoined() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		Conductor cond = new Conductor();
		cond.setName( "Bob" );
		s.persist( cond );
		ProfessionalAgreement agreement = new ProfessionalAgreement();
		agreement.setExpirationDate( new Date() );
		s.persist( agreement );
		Truck truck = new Truck();
		truck.setAgreement( agreement );
		truck.setWeight( 20 );
		truck.setRegistrationNumber( "2003424" );
		truck.setYear( 2005 );
		truck.setCurrentConductor( cond );
		s.persist( truck );
		s.flush();
		s.delete( truck );
		s.delete( agreement );
		s.delete( cond );
		s.getTransaction().rollback();
		s.close();
	}
