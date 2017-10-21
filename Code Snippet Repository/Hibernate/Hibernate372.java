	@Test
	public void testMultiSessionSetIdentityScope() {

		Book book1 = doInJPA( this::entityManagerFactory, entityManager -> {
			return entityManager.find( Book.class, 1L );
		} );

		Book book2 = doInJPA( this::entityManagerFactory, entityManager -> {
			return entityManager.find( Book.class, 1L );
		} );
		//tag::entity-pojo-multi-session-set-identity-scope-example[]

		doInJPA( this::entityManagerFactory, entityManager -> {
			Set<Book> books = new HashSet<>();

			books.add( book1 );
			books.add( book2 );

			assertEquals( 2, books.size() );
		} );
		//end::entity-pojo-multi-session-set-identity-scope-example[]
	}
