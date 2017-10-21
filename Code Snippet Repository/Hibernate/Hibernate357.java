	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Publisher publisher = new Publisher();
			publisher.setId( 1L );
			publisher.setName( "Amazon" );
			entityManager.persist( publisher );

			Book book = new Book();
			book.setId( 1L );
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( "Vlad Mihalcea" );
			book.setProductNumber( "973022823X" );
			book.setPublisher( publisher );

			entityManager.persist( book );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Publisher publisher = entityManager.getReference( Publisher.class, 1L );
			//tag::naturalid-load-access-example[]

			Book book = entityManager
				.unwrap(Session.class)
				.byNaturalId( Book.class )
				.using("productNumber", "973022823X")
				.using("publisher", publisher)
				.load();
			//end::naturalid-load-access-example[]

			assertEquals("High-Performance Java Persistence", book.getTitle());
		} );
	}
