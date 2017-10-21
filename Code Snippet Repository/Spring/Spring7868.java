	@Override
	public Map<String, Object> getJpaPropertyMap() {
		Map<String, Object> jpaProperties = new HashMap<>();

		if (getDatabasePlatform() != null) {
			jpaProperties.put(AvailableSettings.DIALECT, getDatabasePlatform());
		}
		else {
			Class<?> databaseDialectClass = determineDatabaseDialectClass(getDatabase());
			if (databaseDialectClass != null) {
				jpaProperties.put(AvailableSettings.DIALECT, databaseDialectClass.getName());
			}
		}

		if (isGenerateDdl()) {
			jpaProperties.put(AvailableSettings.HBM2DDL_AUTO, "update");
		}
		if (isShowSql()) {
			jpaProperties.put(AvailableSettings.SHOW_SQL, "true");
		}

		if (this.jpaDialect.prepareConnection) {
			// Hibernate 5.1/5.2: manually enforce connection release mode ON_CLOSE (the former default)
			try {
				// Try Hibernate 5.2
				AvailableSettings.class.getField("CONNECTION_HANDLING");
				jpaProperties.put("hibernate.connection.handling_mode", "DELAYED_ACQUISITION_AND_HOLD");
			}
			catch (NoSuchFieldException ex) {
				// Try Hibernate 5.1
				try {
					AvailableSettings.class.getField("ACQUIRE_CONNECTIONS");
					jpaProperties.put("hibernate.connection.release_mode", "ON_CLOSE");
				}
				catch (NoSuchFieldException ex2) {
					// on Hibernate 5.0.x or lower - no need to change the default there
				}
			}
		}

		return jpaProperties;
	}
