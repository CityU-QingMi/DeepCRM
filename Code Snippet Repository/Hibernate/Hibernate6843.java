	private void saveSomeCitizens() {
		Citizen c1 = new Citizen();
		c1.setFirstname( "Emmanuel" );
		c1.setLastname( "Bernard" );
		c1.setSsn( "1234" );

		State france = new State();
		france.setName( "Ile de France" );
		c1.setState( france );

		Citizen c2 = new Citizen();
		c2.setFirstname( "Gavin" );
		c2.setLastname( "King" );
		c2.setSsn( "000" );
		State australia = new State();
		australia.setName( "Australia" );
		c2.setState( australia );

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( australia );
		s.persist( france );
		s.persist( c1 );
		s.persist( c2 );
		tx.commit();
		s.close();
	}
