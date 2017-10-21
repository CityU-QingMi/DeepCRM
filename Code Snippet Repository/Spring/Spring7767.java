	public LocalSessionFactoryBuilder(@Nullable DataSource dataSource, ResourceLoader resourceLoader, MetadataSources metadataSources) {
		super(metadataSources);

		getProperties().put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, SpringSessionContext.class.getName());
		if (dataSource != null) {
			getProperties().put(AvailableSettings.DATASOURCE, dataSource);
		}

		// Hibernate 5.1/5.2: manually enforce connection release mode ON_CLOSE (the former default)
		try {
			// Try Hibernate 5.2
			AvailableSettings.class.getField("CONNECTION_HANDLING");
			getProperties().put("hibernate.connection.handling_mode", "DELAYED_ACQUISITION_AND_HOLD");
		}
		catch (NoSuchFieldException ex) {
			// Try Hibernate 5.1
			try {
				AvailableSettings.class.getField("ACQUIRE_CONNECTIONS");
				getProperties().put("hibernate.connection.release_mode", "ON_CLOSE");
			}
			catch (NoSuchFieldException ex2) {
				// on Hibernate 5.0.x or lower - no need to change the default there
			}
		}

		getProperties().put(AvailableSettings.CLASSLOADERS, Collections.singleton(resourceLoader.getClassLoader()));
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
	}
