	@Test
	public void testPersistForceFlush() {

		//tag::entity-pojo-naive-equals-hashcode-persist-force-flush-example[]
		Book book1 = new Book();
		book1.setTitle( "High-Performance Java Persistence" );

		Book book2 = new Book();
		book2.setTitle( "Java Persistence with Hibernate" );

		Library library = doInJPA( this::entityManagerFactory, entityManager -> {
			Library _library = entityManager.find( Library.class, 1L );

			entityManager.persist( book1 );
			entityManager.persist( book2 );
			entityManager.flush();

			_library.getBooks().add( book1 );
			_library.getBooks().add( book2 );

			return _library;
		} );

		assertTrue( library.getBooks().contains( book1 ) );
		assertTrue( library.getBooks().contains( book2 ) );
		//end::entity-pojo-naive-equals-hashcode-persist-force-flush-example[]
	}
