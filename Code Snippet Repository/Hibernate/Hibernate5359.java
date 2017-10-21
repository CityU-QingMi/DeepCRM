	public static Connection createConnection(String databaseName, int majorVersion, int minorVersion) {
		DatabaseMetaDataHandler metadataHandler = new DatabaseMetaDataHandler( databaseName, majorVersion, minorVersion );
		ConnectionHandler connectionHandler = new ConnectionHandler();

		DatabaseMetaData metadataProxy = ( DatabaseMetaData ) Proxy.newProxyInstance(
				ClassLoader.getSystemClassLoader(),
				new Class[] { DatabaseMetaData.class },
				metadataHandler
		);

		Connection connectionProxy = ( Connection ) Proxy.newProxyInstance(
				ClassLoader.getSystemClassLoader(),
				new Class[] { Connection.class },
				connectionHandler
		);

		metadataHandler.setConnectionProxy( connectionProxy );
		connectionHandler.setMetadataProxy( metadataProxy );

		return connectionProxy;
	}
