	private void getMergedAnnotationAttributesWithHalfConventionBasedAndHalfAliasedComposedAnnotation(Class<?> clazz) {
		String[] expected = asArray("explicitDeclaration");
		String name = ContextConfig.class.getName();
		String simpleName = clazz.getSimpleName();
		AnnotationAttributes attributes = getMergedAnnotationAttributes(clazz, name);

		assertNotNull("Should find @ContextConfig on " + simpleName, attributes);
		assertArrayEquals("locations for class [" + clazz.getSimpleName() + "]", expected, attributes.getStringArray("locations"));
		assertArrayEquals("value for class [" + clazz.getSimpleName() + "]", expected, attributes.getStringArray("value"));

		// Verify contracts between utility methods:
		assertTrue(isAnnotated(clazz, name));
	}
