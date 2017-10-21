	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Subsystem subsystem = entityManager.find(
				Subsystem.class,
				"Hibernate Forum"
			);
			SystemUser systemUser = entityManager.find(
				SystemUser.class,
				new PK(
					subsystem,
					"vlad"
				)
			);

			assertEquals( "Vlad Mihalcea", systemUser.getName() );
		} );

	}
