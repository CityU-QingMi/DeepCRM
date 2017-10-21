	@Test
	void canStreamOverRegisteredExtension() {
		registry.registerExtension(MyExtension.class);

		AtomicBoolean hasRun = new AtomicBoolean(false);

		registry.getExtensions(MyExtensionApi.class).forEach(extension -> {
			assertEquals(MyExtension.class.getName(), extension.getClass().getName());
			hasRun.set(true);
		});

		assertTrue(hasRun.get());
	}
