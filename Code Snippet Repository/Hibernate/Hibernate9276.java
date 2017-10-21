	public Connection makeConnection() throws SQLException {
		if ( driver == null ) {
			try {
				driver = (Driver) Class.forName( DRIVER ).newInstance();
			}
			catch (Exception e) {
				throw new HibernateException( "Unable to load JDBC Driver [" + DRIVER + "]", e );
			}
		}

		return driver.connect( URL, createDriverManagerProperties() );
	}
