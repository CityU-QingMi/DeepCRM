	@Test
	public void testPersist() {

		//tag::entity-pojo-natural-id-equals-hashcode-persist-example[]
		Book book1 = new Book();
		book1.setTitle( "High-Performance Java Persistence" );
		book1.setIsbn( "978-9730228236" );

		Library library = doInJPA( this::entityManagerFactory, entityManager -> {
			Library _library = entityManager.find( Library.class, 1L );

			_library.getBooks().add( book1 );

			return _library;
		} );

		assertTrue( library.getBooks().contains( book1 ) );
		//end::entity-pojo-natural-id-equals-hashcode-persist-example[]
	}
