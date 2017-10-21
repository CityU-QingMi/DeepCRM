	public Properties loadProperties(String resourceName) {
		final InputStream stream = bootstrapServiceRegistry.getService( ClassLoaderService.class ).locateResourceStream( resourceName );

		if ( stream == null ) {
			throw new ConfigurationException( "Unable to apply settings from properties file [" + resourceName + "]" );
		}

		try {
			Properties properties = new Properties();
			properties.load( stream );
			return properties;
		}
		catch (IOException e) {
			throw new ConfigurationException( "Unable to apply settings from properties file [" + resourceName + "]", e );
		}
		finally {
			try {
				stream.close();
			}
			catch (IOException e) {
				log.debug(
						String.format( "Unable to close properties file [%s] stream", resourceName ),
						e
				);
			}
		}
	}
