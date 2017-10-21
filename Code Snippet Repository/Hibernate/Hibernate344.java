	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			SystemUser systemUser = new SystemUser();
			systemUser.setPk( new PK(
				"Hibernate Forum",
				"vlad"
			) );
			systemUser.setName( "Vlad Mihalcea" );

			entityManager.persist( systemUser );
		} );
	}
