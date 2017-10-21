	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			session.doWork( connection -> {
				try(Statement statement = connection.createStatement(); ) {
					statement.executeUpdate( String.format( "ALTER TABLE person %s valid %s",
						getDialect().getAddColumnString(), getDialect().getTypeName( Types.BOOLEAN )));
					statement.executeUpdate( String.format( "ALTER TABLE Person_phones %s valid %s",
						getDialect().getAddColumnString(), getDialect().getTypeName( Types.BOOLEAN )));
				}
			} );
		});
	}
