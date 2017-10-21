	@Test
	public void getMergedAnnotationAttributesWithConventionBasedComposedAnnotation() {
		Class<?> element = ConventionBasedComposedContextConfigClass.class;
		String name = ContextConfig.class.getName();
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, name);

		assertNotNull("Should find @ContextConfig on " + element.getSimpleName(), attributes);
		assertArrayEquals("locations", asArray("explicitDeclaration"), attributes.getStringArray("locations"));
		assertArrayEquals("value", asArray("explicitDeclaration"), attributes.getStringArray("value"));

		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
	}
