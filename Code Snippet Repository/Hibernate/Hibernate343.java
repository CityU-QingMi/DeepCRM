	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Subsystem subsystem = new Subsystem();
			subsystem.setId( "Hibernate Forum" );
			subsystem.setDescription( "Hibernate projects forum" );
			entityManager.persist( subsystem );

			SystemUser systemUser = new SystemUser();
			systemUser.setPk( new PK(
				subsystem,
				"vlad"
			) );
			systemUser.setName( "Vlad Mihalcea" );

			entityManager.persist( systemUser );
		} );
	}
