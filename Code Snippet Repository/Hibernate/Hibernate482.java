	@Test
	@TestForIssue( jiraKey = "" )
	public void testJdbc41() {
		doInHibernate( this::sessionFactory, session -> {
			session.doWork( connection -> {
				//Connection#getSchema was added in Java 1.7
				String schema = connection.getSchema();
				assertNotNull(schema);
			} );
		} );
	}
