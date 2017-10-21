	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Author author = new Author();
			author.setId( 1L );
			author.setName( "John Doe" );
			author.setEmail( "john@acme.com" );

			entityManager.persist( author );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::naturalid-mutable-synchronized-example[]
			//tag::naturalid-mutable-example[]
			Author author = entityManager
				.unwrap(Session.class)
				.bySimpleNaturalId( Author.class )
				.load( "john@acme.com" );
			//end::naturalid-mutable-example[]

			author.setEmail( "john.doe@acme.com" );

			assertNull(
				entityManager
					.unwrap(Session.class)
					.bySimpleNaturalId( Author.class )
					.setSynchronizationEnabled( false )
					.load( "john.doe@acme.com" )
			);

			assertSame( author,
				entityManager
					.unwrap(Session.class)
					.bySimpleNaturalId( Author.class )
					.setSynchronizationEnabled( true )
					.load( "john.doe@acme.com" )
			);
			//end::naturalid-mutable-example[]

			//end::naturalid-mutable-synchronized-example[]
		} );
	}
