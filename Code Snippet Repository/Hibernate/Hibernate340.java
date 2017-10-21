	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Book book = new Book();
			book.setId( 1L );
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( "Vlad Mihalcea" );
			book.setIsbn( "978-9730228236" );

			entityManager.persist( book );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::naturalid-cacheable-load-access-example[]
			Book book = entityManager
				.unwrap(Session.class)
				.bySimpleNaturalId( Book.class )
				.load( "978-9730228236" );
			//end::naturalid-cacheable-load-access-example[]

			assertEquals("High-Performance Java Persistence", book.getTitle());
		} );
	}
