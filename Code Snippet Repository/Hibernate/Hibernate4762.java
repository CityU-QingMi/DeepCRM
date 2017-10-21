	private void configure(StandardServiceRegistryBuilder registryBuilder) throws IOException {
		if ( configurationFile != null ) {
			registryBuilder.configure( configurationFile );
		}

		Properties properties = new Properties();
		if ( propertiesFile == null ) {
			properties.putAll( getProject().getProperties() );
		}
		else {
			try (FileInputStream fip = new FileInputStream( propertiesFile )){
				properties.load( fip );
			} 
		}

		registryBuilder.applySettings( properties );
	}
