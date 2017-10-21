	@Test
	public void test() {
		Book book = new Book();

		doInJPA( this::entityManagerFactory, entityManager -> {
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( "Vlad Mihalcea" );

			entityManager.persist( book );
		} );

		assertNotNull( book.getId() );
	}
