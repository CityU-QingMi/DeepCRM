	static TestInstance.Lifecycle getTestInstanceLifecycle(Class<?> testClass, ConfigurationParameters configParams) {
		Preconditions.notNull(testClass, "testClass must not be null");
		Preconditions.notNull(configParams, "ConfigurationParameters must not be null");

		// @formatter:off
		return AnnotationUtils.findAnnotation(testClass, TestInstance.class)
				.map(TestInstance::value)
				.orElseGet(() -> getDefaultTestInstanceLifecycle(configParams));
		// @formatter:on
	}
