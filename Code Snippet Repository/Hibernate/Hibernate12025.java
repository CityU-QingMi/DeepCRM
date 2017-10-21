	public JdbcProperties() {
		Properties connectionProperties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream( "hibernate.properties" );
			try {
				connectionProperties.load( inputStream );
				url = connectionProperties.getProperty(
						"hibernate.connection.url" );
				user = connectionProperties.getProperty(
						"hibernate.connection.username" );
				password = connectionProperties.getProperty(
						"hibernate.connection.password" );
			}
			catch ( IOException e ) {
				throw new IllegalArgumentException( e );
			}
		}
		finally {
			try {
				if ( inputStream != null ) {
					inputStream.close();
				}
			}
			catch ( IOException ignore ) {
				log.error( ignore.getMessage() );
			}
		}
	}
