	protected ConfigurableApplicationContext createApplicationContext(BootstrapContext bootstrapContext) {
		ResourceAdapterApplicationContext applicationContext =
				new ResourceAdapterApplicationContext(bootstrapContext);

		// Set ResourceAdapter's ClassLoader as bean class loader.
		applicationContext.setClassLoader(getClass().getClassLoader());

		// Extract individual config locations.
		String[] configLocations =
				StringUtils.tokenizeToStringArray(getContextConfigLocation(), CONFIG_LOCATION_DELIMITERS);

		loadBeanDefinitions(applicationContext, configLocations);
		applicationContext.refresh();

		return applicationContext;
	}
