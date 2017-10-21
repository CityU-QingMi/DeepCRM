	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			PostalCarrier postalCarrier = new PostalCarrier();
			postalCarrier.setId( 1L );
			postalCarrier.setPostalCode( new PostalCode() );
			postalCarrier.getPostalCode().setCode( "ABC123" );
			postalCarrier.getPostalCode().setCountry( "US" );

			entityManager.persist( postalCarrier );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			PostalCarrier postalCarrier = entityManager.unwrap( Session.class )
					.byNaturalId( PostalCarrier.class )
					.using( "postalCode", new PostalCode( "ABC123", "US" ) )
					.load();
			assertEquals( Long.valueOf( 1L ), postalCarrier.getId() );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			PostalCarrier postalCarrier = entityManager.unwrap( Session.class )
					.bySimpleNaturalId( PostalCarrier.class )
					.load( new PostalCode( "ABC123", "US" ) );
			assertEquals( Long.valueOf( 1L ), postalCarrier.getId() );
		} );
	}
