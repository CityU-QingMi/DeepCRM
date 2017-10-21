	@Test
	public void testMultiSessionIdentityScope() {

		//tag::entity-pojo-multi-session-identity-scope-example[]
		Book book1 = doInJPA( this::entityManagerFactory, entityManager -> {
			return entityManager.find( Book.class, 1L );
		} );

		Book book2 = doInJPA( this::entityManagerFactory, entityManager -> {
			return entityManager.find( Book.class, 1L );
		} );

		assertFalse( book1 == book2 );
		//end::entity-pojo-multi-session-identity-scope-example[]
	}
