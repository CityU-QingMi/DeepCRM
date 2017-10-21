	@Test
	public void testLifecycle() {
		doInHibernate( this::sessionFactory, session -> {
			Country canada = new Country();
			canada.setName( "Canada" );
			session.persist( canada );

			Country usa = new Country();
			usa.setName( "USA" );
			session.persist( usa );
		} );

		doInHibernate( this::sessionFactory, session -> {
			Country canada = session.byNaturalId( Country.class ).using( "name", "Canada" ).load();
			Country usa = session.byNaturalId( Country.class ).using( "name", "USA" ).load();

			Book book = new Book();
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( "Vlad Mihalcea" );
			book.setEbookPublisher( new Publisher( "Leanpub", canada ) );
			book.setPaperBackPublisher( new Publisher( "Amazon", usa ) );

			session.persist( book );
		} );
	}
