	@Nullable
	protected Class<? extends ContextLoader> resolveExplicitContextLoaderClass(
			List<ContextConfigurationAttributes> configAttributesList) {

		Assert.notNull(configAttributesList, "ContextConfigurationAttributes list must not be null");

		for (ContextConfigurationAttributes configAttributes : configAttributesList) {
			if (logger.isTraceEnabled()) {
				logger.trace(String.format("Resolving ContextLoader for context configuration attributes %s",
						configAttributes));
			}
			Class<? extends ContextLoader> contextLoaderClass = configAttributes.getContextLoaderClass();
			if (ContextLoader.class != contextLoaderClass) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format(
							"Found explicit ContextLoader class [%s] for context configuration attributes %s",
							contextLoaderClass.getName(), configAttributes));
				}
				return contextLoaderClass;
			}
		}
		return null;
	}
