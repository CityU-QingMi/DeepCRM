	@Test
	void canOverrideAttributeWhenContextIsExtended() {
		ClassExtensionContext extensionContext = new ClassExtensionContext(null, null, null, null);
		ExtensionRegistry extensionRegistry = ExtensionRegistry.createRegistryWithDefaultExtensions(configParams);
		TestInstanceProvider testInstanceProvider = mock(TestInstanceProvider.class);
		ClassExtensionContext newExtensionContext = new ClassExtensionContext(extensionContext, null, null, null);

		JupiterEngineExecutionContext newContext = originalContext.extend() //
				.withExtensionContext(extensionContext) //
				.withExtensionRegistry(extensionRegistry) //
				.withTestInstanceProvider(testInstanceProvider) //
				.build() //
				.extend() //
				.withExtensionContext(newExtensionContext) //
				.build();

		assertSame(newExtensionContext, newContext.getExtensionContext());
		assertSame(extensionRegistry, newContext.getExtensionRegistry());
		assertSame(testInstanceProvider, newContext.getTestInstanceProvider());
	}
