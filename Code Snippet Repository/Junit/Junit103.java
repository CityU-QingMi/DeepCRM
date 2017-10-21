	public static ExtensionRegistry createRegistryWithDefaultExtensions(ConfigurationParameters configParams) {
		ExtensionRegistry extensionRegistry = new ExtensionRegistry(null);

		// @formatter:off
		logger.trace(() -> "Registering default extensions: " + DEFAULT_EXTENSIONS.stream()
						.map(extension -> extension.getClass().getName())
						.collect(toList()));
		// @formatter:on

		DEFAULT_EXTENSIONS.forEach(extensionRegistry::registerDefaultExtension);

		if (configParams.getBoolean(EXTENSIONS_AUTODETECTION_ENABLED_PROPERTY_NAME).orElse(Boolean.FALSE)) {
			registerAutoDetectedExtensions(extensionRegistry);
		}

		return extensionRegistry;
	}
