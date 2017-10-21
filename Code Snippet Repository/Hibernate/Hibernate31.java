	@Test
	public void testLifecycle() {
		//tag::identifiers-derived-mapsid-persist-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( "ABC-123" );
			person.setId( 1L );
			entityManager.persist( person );

			PersonDetails personDetails = new PersonDetails();
			personDetails.setNickName( "John Doe" );
			personDetails.setPerson( person );

			entityManager.persist( personDetails );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			PersonDetails personDetails = entityManager.find( PersonDetails.class, 1L );

			assertEquals("John Doe", personDetails.getNickName());
		} );
		//end::identifiers-derived-mapsid-persist-example[]
	}
