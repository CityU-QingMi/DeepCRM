	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::events-default-listener-persist-example[]
			Person author = new Person();
			author.setId( 1L );
			author.setName( "Vlad Mihalcea" );

			entityManager.persist( author );

			Book book = new Book();
			book.setId( 1L );
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( author );

			entityManager.persist( book );
			//end::events-default-listener-persist-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::events-default-listener-update-example[]
			Person author = entityManager.find( Person.class, 1L );
			author.setName( "Vlad-Alexandru Mihalcea" );

			Book book = entityManager.find( Book.class, 1L );
			book.setTitle( "High-Performance Java Persistence 2nd Edition" );
			//end::events-default-listener-update-example[]
		} );
	}
