	@After
	public void tearsDown() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			log.error( e.getMessage() );
		}
		connectionProvider.stop();
		StandardServiceRegistryBuilder.destroy( ssr );
	}
