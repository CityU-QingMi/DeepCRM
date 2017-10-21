	@Test
	void registerExtensionByImplementingClass() {
		registry.registerExtension(MyExtension.class);

		assertExtensionRegistered(registry, MyExtension.class);

		registry.registerExtension(MyExtension.class);
		registry.registerExtension(MyExtension.class);
		registry.registerExtension(MyExtension.class);

		assertEquals(1, registry.getExtensions(MyExtension.class).size());
		assertExtensionRegistered(registry, MyExtension.class);
		assertEquals(1, countExtensions(registry, MyExtensionApi.class));

		registry.registerExtension(YourExtension.class);
		assertExtensionRegistered(registry, YourExtension.class);
		assertEquals(2, countExtensions(registry, MyExtensionApi.class));
	}
