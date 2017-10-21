	private void assertGetMergedAnnotation(Class<?> element, String... expected) {
		String name = ContextConfig.class.getName();
		ContextConfig contextConfig = getMergedAnnotation(element, ContextConfig.class);

		assertNotNull("Should find @ContextConfig on " + element.getSimpleName(), contextConfig);
		assertArrayEquals("locations", expected, contextConfig.locations());
		assertArrayEquals("value", expected, contextConfig.value());
		assertArrayEquals("classes", new Class<?>[0], contextConfig.classes());

		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
	}
