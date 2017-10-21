	@Test
	public void test() {
		SystemUser _systemUser = doInJPA( this::entityManagerFactory, entityManager -> {
			SystemUser systemUser = new SystemUser();
			systemUser.setId(
					new PK(
							"Hibernate Forum",
							"vlad"
					)
			);
			systemUser.setName( "Vlad Mihalcea" );

			entityManager.persist( systemUser );

			return systemUser;
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			SystemUser systemUser = entityManager.find(
				SystemUser.class,
				_systemUser.getId()
			);

			assertEquals( "Vlad Mihalcea", systemUser.getName() );
		} );

	}
