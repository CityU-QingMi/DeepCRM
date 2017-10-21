	private void assertDefaultGlobalExtensionsAreRegistered() {
		assertExtensionRegistered(registry, DisabledCondition.class);
		assertExtensionRegistered(registry, RepeatedTestExtension.class);
		assertExtensionRegistered(registry, TestInfoParameterResolver.class);
		assertExtensionRegistered(registry, TestReporterParameterResolver.class);

		assertEquals(2, countExtensions(registry, ParameterResolver.class));
		assertEquals(1, countExtensions(registry, ExecutionCondition.class));
		assertEquals(1, countExtensions(registry, TestTemplateInvocationContextProvider.class));
	}
