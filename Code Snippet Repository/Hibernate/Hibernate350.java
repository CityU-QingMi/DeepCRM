	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			SystemUser systemUser = new SystemUser();
			systemUser.setId(
				new PK(
					"Hibernate Forum",
					"vlad"
				)
			);
			systemUser.setName( "Vlad Mihalcea" );

			entityManager.persist( systemUser );
		} );
	}
