	@Test
	public void testPersist() {

		//tag::entity-pojo-naive-equals-hashcode-persist-example[]
		Book book1 = new Book();
		book1.setTitle( "High-Performance Java Persistence" );

		Book book2 = new Book();
		book2.setTitle( "Java Persistence with Hibernate" );

		Library library = doInJPA( this::entityManagerFactory, entityManager -> {
			Library _library = entityManager.find( Library.class, 1L );

			_library.getBooks().add( book1 );
			_library.getBooks().add( book2 );

			return _library;
		} );

		assertFalse( library.getBooks().contains( book1 ) );
		assertFalse( library.getBooks().contains( book2 ) );
		//end::entity-pojo-naive-equals-hashcode-persist-example[]
	}
