	static TestInstance.Lifecycle getDefaultTestInstanceLifecycle(ConfigurationParameters configParams) {
		Preconditions.notNull(configParams, "ConfigurationParameters must not be null");
		String propertyName = DEFAULT_TEST_INSTANCE_LIFECYCLE_PROPERTY_NAME;

		Optional<String> optional = configParams.get(propertyName);
		String constantName = null;
		if (optional.isPresent()) {
			try {
				constantName = optional.get().trim().toUpperCase();
				Lifecycle lifecycle = TestInstance.Lifecycle.valueOf(constantName);
				logger.info(() -> String.format(
					"Using default test instance lifecycle mode '%s' set via the '%s' configuration parameter.",
					lifecycle, propertyName));
				return lifecycle;
			}
			catch (Exception ex) {
				// local copy necessary for use in lambda expression
				String constant = constantName;
				logger.warn(() -> String.format(
					"Invalid test instance lifecycle mode '%s' set via the '%s' configuration parameter. "
							+ "Falling back to %s lifecycle semantics.",
					constant, propertyName, Lifecycle.PER_METHOD.name()));
			}
		}

		return Lifecycle.PER_METHOD;
	}
