	private Properties getConfigProperties(String resource) {
		try {
			Properties properties = new Properties();
			properties.load( classLoaderService.locateResourceStream( resource ) );
			return properties;
		}
		catch (IOException e) {
			throw new HibernateException( "Unable to load properties from specified config file: " + resource, e );
		}
	}
