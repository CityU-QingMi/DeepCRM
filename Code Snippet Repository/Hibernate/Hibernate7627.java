	protected void buildSessionFactory() {
		BootstrapServiceRegistry bootRegistry = buildBootstrapServiceRegistry();
		StandardServiceRegistryImpl _serviceRegistry = buildServiceRegistry( bootRegistry, constructConfiguration() );

		try {
			try (Connection connection = _serviceRegistry.getService( JdbcServices.class ).getBootstrapJdbcConnectionAccess().obtainConnection();
				 Statement statement = connection.createStatement()) {
				connection.setAutoCommit( true );
				statement.executeUpdate( "DROP DATABASE hibernate_orm_test_collation" );
			}
			catch (SQLException e) {
				log.debug( e.getMessage() );
			}
			try (Connection connection = _serviceRegistry.getService( JdbcServices.class ).getBootstrapJdbcConnectionAccess().obtainConnection();
				 Statement statement = connection.createStatement()) {
				connection.setAutoCommit( true );
				statement.executeUpdate( "CREATE DATABASE hibernate_orm_test_collation COLLATE Latin1_General_CS_AS" );
				statement.executeUpdate( "ALTER DATABASE [hibernate_orm_test_collation] SET AUTO_CLOSE OFF " );
			}
			catch (SQLException e) {
				log.debug( e.getMessage() );
			}
		}
		finally {
			_serviceRegistry.destroy();
		}
		super.buildSessionFactory();
	}
