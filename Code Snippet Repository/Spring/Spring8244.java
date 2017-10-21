	@Override
	@Nullable
	public ContextCustomizer createContextCustomizer(Class<?> testClass,
			List<ContextConfigurationAttributes> configAttributes) {

		if (webSocketPresent && isAnnotatedWithWebAppConfiguration(testClass)) {
			try {
				Class<?> clazz = ClassUtils.forName(MOCK_SERVER_CONTAINER_CONTEXT_CUSTOMIZER_CLASS_NAME,
						getClass().getClassLoader());
				return (ContextCustomizer) BeanUtils.instantiateClass(clazz);
			}
			catch (Throwable ex) {
				throw new IllegalStateException("Failed to enable WebSocket test support; could not load class: " +
						MOCK_SERVER_CONTAINER_CONTEXT_CUSTOMIZER_CLASS_NAME, ex);
			}
		}

		// Else, nothing to customize
		return null;
	}
