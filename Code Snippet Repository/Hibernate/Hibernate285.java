	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Book book = new Book();
			book.setId( 1L );
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( new Author(
				"Vlad",
				"Mihalcea"
			) );

			entityManager.persist( book );
		} );
	}
