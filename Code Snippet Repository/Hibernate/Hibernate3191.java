	public static InputStream getConfigStream(final String path) throws HibernateException {
		final URL url = ConfigHelper.locateConfig( path );

		if ( url == null ) {
			String msg = LOG.unableToLocateConfigFile( path );
			LOG.error( msg );
			throw new HibernateException( msg );
		}

		try {
			return url.openStream();
		}
		catch (IOException e) {
			throw new HibernateException( "Unable to open config file: " + path, e );
		}
	}
