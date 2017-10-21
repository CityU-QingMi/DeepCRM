	private static void registerAutoDetectedExtensions(ExtensionRegistry extensionRegistry) {
		Iterable<Extension> extensions = ServiceLoader.load(Extension.class, ClassLoaderUtils.getDefaultClassLoader());

		// @formatter:off
		logger.config(() -> "Registering auto-detected extensions: "
				+ StreamSupport.stream(extensions.spliterator(), false)
						.map(extension -> extension.getClass().getName())
						.collect(toList()));
		// @formatter:on

		extensions.forEach(extensionRegistry::registerDefaultExtension);
	}
