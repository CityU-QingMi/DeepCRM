	private static Properties createProperties() {
		final Properties properties = new Properties();
		// If configured in the environment, add configuration file name to properties.
		final String cfgFileName =
				  (String) Environment.getProperties().get( INFINISPAN_CONFIG_RESOURCE_PROP );
		if ( cfgFileName != null ) {
			properties.put( INFINISPAN_CONFIG_RESOURCE_PROP, cfgFileName );
		}
		return properties;
	}
