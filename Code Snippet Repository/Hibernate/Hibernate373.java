	@Test
	public void testTransientSetIdentityScope() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::entity-pojo-transient-set-identity-scope-example[]
			Library library = entityManager.find( Library.class, 1L );

			Book book1 = new Book();
			book1.setId( 100L );
			book1.setTitle( "High-Performance Java Persistence" );

			Book book2 = new Book();
			book2.setId( 101L );
			book2.setTitle( "Java Persistence with Hibernate" );

			library.getBooks().add( book1 );
			library.getBooks().add( book2 );

			assertEquals( 2, library.getBooks().size() );
			//end::entity-pojo-transient-set-identity-scope-example[]
		} );
	}
