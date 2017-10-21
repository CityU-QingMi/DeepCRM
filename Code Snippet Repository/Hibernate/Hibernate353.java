	@Test
	public void test() {
		Author author = new Author();
		Publisher publisher = new Publisher();

		doInJPA( this::entityManagerFactory, entityManager -> {
			author.setName( "Vlad Mihalcea" );
			entityManager.persist( author );

			publisher.setName( "Amazon" );
			entityManager.persist( publisher );

			Book book = new Book();
			book.setAuthor( author );
			book.setPublisher( publisher );
			book.setTitle( "High-Performance Java Persistence" );
			entityManager.persist( book );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::identifiers-composite-id-fetching-example[]
			Book book = entityManager.find( Book.class, new Book(
				author,
				publisher,
				"High-Performance Java Persistence"
			) );

			assertEquals( "Vlad Mihalcea", book.getAuthor().getName() );
			//end::identifiers-composite-id-fetching-example[]
		} );

	}
