	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			session.doWork( connection -> {
				try(Statement statement = connection.createStatement(); ) {
					statement.executeUpdate( "ALTER TABLE person ADD valid NUMBER(1) DEFAULT 0 NOT NULL" );
					//tag::sql-sp-soft-delete-example[]
					statement.executeUpdate(
						"CREATE OR REPLACE PROCEDURE sp_delete_person ( " +
						"   personId IN NUMBER ) " +
						"AS  " +
						"BEGIN " +
						"    UPDATE person SET valid = 0 WHERE id = personId; " +
						"END;"
					);}
				//end::sql-sp-soft-delete-example[]
			} );
		});
	}
