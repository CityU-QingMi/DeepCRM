	private PooledConnections(
			Builder builder) {
		log.debugf( "Initializing Connection pool with %s Connections", builder.initialSize );
		connectionCreator = builder.connectionCreator;
		autoCommit = builder.autoCommit;
		maxSize = builder.maxSize;
		minSize = builder.minSize;
		log.hibernateConnectionPoolSize( maxSize, minSize );
		addConnections( builder.initialSize );
	}
