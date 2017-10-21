	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			SystemUser systemUser = entityManager.find(
				SystemUser.class,
				new PK(
					"Hibernate Forum",
					"vlad"
				)
			);

			assertEquals( "Vlad Mihalcea", systemUser.getName() );
		} );

	}
