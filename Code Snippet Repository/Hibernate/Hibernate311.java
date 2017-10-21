	@Test
	public void testLifecycle() {
		//tag::mapping-JoinColumnOrFormula-persistence-example[]
		Country US = new Country();
		US.setId( 1 );
		US.setDefault( true );
		US.setPrimaryLanguage( "English" );
		US.setName( "United States" );

		Country Romania = new Country();
		Romania.setId( 40 );
		Romania.setDefault( true );
		Romania.setName( "Romania" );
		Romania.setPrimaryLanguage( "Romanian" );

		doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.persist( US );
			entityManager.persist( Romania );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			User user1 = new User( );
			user1.setId( 1L );
			user1.setFirstName( "John" );
			user1.setLastName( "Doe" );
			user1.setLanguage( "English" );
			entityManager.persist( user1 );

			User user2 = new User( );
			user2.setId( 2L );
			user2.setFirstName( "Vlad" );
			user2.setLastName( "Mihalcea" );
			user2.setLanguage( "Romanian" );
			entityManager.persist( user2 );

		} );
		//end::mapping-JoinColumnOrFormula-persistence-example[]

		//tag::mapping-JoinColumnOrFormula-fetching-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "Fetch User entities" );

			User john = entityManager.find( User.class, 1L );
			assertEquals( US, john.getCountry());

			User vlad = entityManager.find( User.class, 2L );
			assertEquals( Romania, vlad.getCountry());
		} );
		//end::mapping-JoinColumnOrFormula-fetching-example[]
	}
