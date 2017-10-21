	void assertAttributes(ContextConfigurationAttributes attributes, Class<?> expectedDeclaringClass,
			String[] expectedLocations, Class<?>[] expectedClasses,
			Class<? extends ContextLoader> expectedContextLoaderClass, boolean expectedInheritLocations) {

		assertEquals("declaring class", expectedDeclaringClass, attributes.getDeclaringClass());
		assertArrayEquals("locations", expectedLocations, attributes.getLocations());
		assertArrayEquals("classes", expectedClasses, attributes.getClasses());
		assertEquals("inherit locations", expectedInheritLocations, attributes.isInheritLocations());
		assertEquals("context loader", expectedContextLoaderClass, attributes.getContextLoaderClass());
	}
