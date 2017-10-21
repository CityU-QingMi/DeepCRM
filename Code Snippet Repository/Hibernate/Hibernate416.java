	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			session.doWork( connection -> {
				try(Statement statement = connection.createStatement(); ) {
					statement.executeUpdate( "ALTER TABLE person ADD COLUMN valid boolean" );
					statement.executeUpdate( "ALTER TABLE Person_phones ADD COLUMN valid boolean" );
				}
			} );
		});
	}
