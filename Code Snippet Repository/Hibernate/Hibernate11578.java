	private List<Citizen> saveSomeCitizens() throws Exception {
		final Citizen c1 = new Citizen();
		c1.setFirstname( "Emmanuel" );
		c1.setLastname( "Bernard" );
		c1.setSsn( "1234" );

		final State france = new State();
		france.setName( "Ile de France" );
		c1.setState( france );

		final Citizen c2 = new Citizen();
		c2.setFirstname( "Gavin" );
		c2.setLastname( "King" );
		c2.setSsn( "000" );
		final State australia = new State();
		australia.setName( "Australia" );
		c2.setState( australia );

		withTxSession(s -> {
			s.persist( australia );
			s.persist( france );
			s.persist( c1 );
			s.persist( c2 );
		});

		List<Citizen> citizens = new ArrayList<>(2);
		citizens.add(c1);
		citizens.add(c2);
		return citizens;
	}
