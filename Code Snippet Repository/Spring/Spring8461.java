	@Override
	public void processContextConfiguration(ContextConfigurationAttributes configAttributes) {
		// Detect default XML configuration files:
		super.processContextConfiguration(configAttributes);

		// Detect default configuration classes:
		if (!configAttributes.hasClasses() && isGenerateDefaultLocations()) {
			configAttributes.setClasses(detectDefaultConfigurationClasses(configAttributes.getDeclaringClass()));
		}
	}
