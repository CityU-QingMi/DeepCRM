	private PooledConnections buildPool(Map configurationValues) {
		final boolean autoCommit = ConfigurationHelper.getBoolean(
				AvailableSettings.AUTOCOMMIT,
				configurationValues,
				false
		);
		final int minSize = ConfigurationHelper.getInt( MIN_SIZE, configurationValues, 1 );
		final int maxSize = ConfigurationHelper.getInt( AvailableSettings.POOL_SIZE, configurationValues, 20 );
		final int initialSize = ConfigurationHelper.getInt( INITIAL_SIZE, configurationValues, minSize );

		ConnectionCreator connectionCreator = buildCreator( configurationValues );
		PooledConnections.Builder pooledConnectionBuilder = new PooledConnections.Builder(
				connectionCreator,
				autoCommit
		);
		pooledConnectionBuilder.initialSize( initialSize );
		pooledConnectionBuilder.minSize( minSize );
		pooledConnectionBuilder.maxSize( maxSize );

		return pooledConnectionBuilder.build();
	}
