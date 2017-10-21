	private static void convertContextConfigToConfigAttributesAndAddToList(ContextConfiguration contextConfiguration,
			Class<?> declaringClass, final List<ContextConfigurationAttributes> attributesList) {

		if (logger.isTraceEnabled()) {
			logger.trace(String.format("Retrieved @ContextConfiguration [%s] for declaring class [%s].",
					contextConfiguration, declaringClass.getName()));
		}
		ContextConfigurationAttributes attributes =
				new ContextConfigurationAttributes(declaringClass, contextConfiguration);
		if (logger.isTraceEnabled()) {
			logger.trace("Resolved context configuration attributes: " + attributes);
		}
		attributesList.add(attributes);
	}
