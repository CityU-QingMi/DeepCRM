	static Set<Class<? extends ApplicationContextInitializer<?>>> resolveInitializerClasses(
			List<ContextConfigurationAttributes> configAttributesList) {
		Assert.notEmpty(configAttributesList, "ContextConfigurationAttributes list must not be empty");

		final Set<Class<? extends ApplicationContextInitializer<?>>> initializerClasses = //
				new HashSet<>();

		for (ContextConfigurationAttributes configAttributes : configAttributesList) {
			if (logger.isTraceEnabled()) {
				logger.trace(String.format("Processing context initializers for context configuration attributes %s",
					configAttributes));
			}

			initializerClasses.addAll(Arrays.asList(configAttributes.getInitializers()));

			if (!configAttributes.isInheritInitializers()) {
				break;
			}
		}

		return initializerClasses;
	}
