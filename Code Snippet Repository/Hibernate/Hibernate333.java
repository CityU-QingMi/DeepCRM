	@Test
	public void testLifecycle() {

		doInJPA( this::entityManagerFactory, entityManager -> {

			Book book = new Book();
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( "Vlad Mihalcea" );
			book.setPublisher(
				new Publisher(
					"Amazon",
					new Location(
						"USA",
						"Seattle"
					)
				)
			);

			entityManager.persist( book );
		} );
	}
