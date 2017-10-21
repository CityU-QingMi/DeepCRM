	@Test
	void newRegistryWithoutParentHasDefaultExtensionsPlusAutodetectedExtensionsLoadedViaServiceLoader() {

		when(configParams.getBoolean(Constants.EXTENSIONS_AUTODETECTION_ENABLED_PROPERTY_NAME)).thenReturn(
			Optional.of(Boolean.TRUE));
		registry = createRegistryWithDefaultExtensions(configParams);

		List<Extension> extensions = registry.getExtensions(Extension.class);

		assertEquals(5, extensions.size());
		assertDefaultGlobalExtensionsAreRegistered();

		assertExtensionRegistered(registry, ServiceLoaderExtension.class);
		assertEquals(1, countExtensions(registry, BeforeAllCallback.class));
	}
