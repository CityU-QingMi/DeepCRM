	@AfterClassOnce
	public void tearDown() {
		final Session session = openSession();
		session.getTransaction().begin();
		session.doWork( new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement statement = null;
				try {
					statement = connection.prepareStatement( "DROP TABLE test_data" );
					statement.execute();
				}
				finally {
					if ( statement != null ) {
						statement.close();
					}
				}
			}
		} );
		session.getTransaction().commit();
		session.close();
	}
