	private Set<ContextCustomizer> getContextCustomizers(Class<?> testClass,
			List<ContextConfigurationAttributes> configAttributes) {

		List<ContextCustomizerFactory> factories = getContextCustomizerFactories();
		Set<ContextCustomizer> customizers = new LinkedHashSet<>(factories.size());
		for (ContextCustomizerFactory factory : factories) {
			ContextCustomizer customizer = factory.createContextCustomizer(testClass, configAttributes);
			if (customizer != null) {
				customizers.add(customizer);
			}
		}
		return customizers;
	}
