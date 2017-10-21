	@Test
	@TestForIssue( jiraKey = "" )
	public void testJdbc42() {
		doInHibernate( this::sessionFactory, session -> {
			for ( int i = 0; i < 5; i++ ) {
				IrrelevantEntity entity = new IrrelevantEntity();
				entity.setName( getClass().getName() );
				session.persist( entity );
			}
			session.flush();
			session.doWork( connection -> {
				try( Statement statement = connection.createStatement()) {
					statement.executeUpdate( "DELETE FROM IrrelevantEntity" );
					assertEquals( 5, statement.getLargeUpdateCount());
				}
			} );
		} );
	}
