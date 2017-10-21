	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Country canada = new Country();
			canada.setName( "Canada" );
			entityManager.persist( canada );

			Country usa = new Country();
			usa.setName( "USA" );
			entityManager.persist( usa );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			Country canada = session.byNaturalId( Country.class ).using( "name", "Canada" ).load();
			Country usa = session.byNaturalId( Country.class ).using( "name", "USA" ).load();

			Book book = new Book();
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( "Vlad Mihalcea" );
			book.setEbookPublisher( new Publisher( "Leanpub", canada ) );
			book.setPaperBackPublisher( new Publisher( "Amazon", usa ) );

			entityManager.persist( book );
		} );
	}
