	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::mapping-model-dynamic-example[]

			Map<String, String> book = new HashMap<>();
			book.put( "isbn", "978-9730228236" );
			book.put( "title", "High-Performance Java Persistence" );
			book.put( "author", "Vlad Mihalcea" );

			entityManager
				.unwrap(Session.class)
				.save( "Book", book );
			//end::mapping-model-dynamic-example[]
		} );
	}
