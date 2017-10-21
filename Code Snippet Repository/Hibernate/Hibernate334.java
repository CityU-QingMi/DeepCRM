	@Test
	public void testLifecycle() {

		doInJPA( this::entityManagerFactory, entityManager -> {

			Book book = new Book();
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( "Vlad Mihalcea" );
			book.setPublisherName("Amazon");
			book.setPublisherCountry("USA");

			entityManager.persist( book );
		} );
	}
