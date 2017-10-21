	private static void populateTestData(EntityManager entityManager) {
		entityManager.getTransaction().begin();

		if ( !hasData( entityManager ) ) {
			Person p1 = new Person();

			Address a1 = new Address();

			p1.setName( "James" );
			p1.setSurname( "Bond" );
			p1.setAddress( a1 );

			a1.setStreetName( "MI6" );
			a1.setHouseNumber( 18 );
			a1.setFlatNumber( 25 );
			a1.setPersons( new HashSet<Person>() );
			a1.getPersons().add( p1 );

			entityManager.persist( a1 );

			entityManager.persist( p1 );

			System.out.println( "The DB was populated with example data." );
		}

		entityManager.getTransaction().commit();
	}
