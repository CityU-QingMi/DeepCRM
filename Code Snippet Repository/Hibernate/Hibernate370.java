	@Test
	public void testSetIdentityScope() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::entity-pojo-set-identity-scope-example[]
			Library library = entityManager.find( Library.class, 1L );

			Book book1 = entityManager.find( Book.class, 1L );
			Book book2 = entityManager.find( Book.class, 1L );

			library.getBooks().add( book1 );
			library.getBooks().add( book2 );

			assertEquals( 1, library.getBooks().size() );
			//end::entity-pojo-set-identity-scope-example[]
		} );
	}
