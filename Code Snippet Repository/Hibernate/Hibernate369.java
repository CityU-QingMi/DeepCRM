	@Test
	public void testIdentityScope() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::entity-pojo-identity-scope-example[]
			Book book1 = entityManager.find( Book.class, 1L );
			Book book2 = entityManager.find( Book.class, 1L );

			assertTrue( book1 == book2 );
			//end::entity-pojo-identity-scope-example[]
		} );

	}
