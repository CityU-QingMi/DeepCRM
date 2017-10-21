	@Test
	@FailureExpected( jiraKey = "" )
	public void testQueryConflictingPropertyName() {
		doInHibernate( this::sessionFactory, session -> {
			Town town = new Town( 1L, "London", 5000000 );
			Country country = new Country( 2L, "Andorra", 10000 );
			Mountain mountain = new Mountain( 3L, "Mont Blanc", 4810 );
			session.persist( town );
			session.persist( country );
			session.persist( mountain );
		} );
		doInHibernate( this::sessionFactory, session -> {
			List<Place> places = session.createQuery(
				"select pl from " + Place.class.getName() + " pl " +
				" where pl.population > 1000" )
			.getResultList();

			//Expected list of length 2. Expected London and Andorra
			assertEquals( 2L, places.size() );
		} );
	}
