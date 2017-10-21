	@Test
	public void getMergedAnnotationAttributesWithAliasedValueComposedAnnotation() {
		Class<?> element = AliasedValueComposedContextConfigClass.class;
		String name = ContextConfig.class.getName();
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, name);

		assertNotNull("Should find @ContextConfig on " + element.getSimpleName(), attributes);
		assertArrayEquals("locations", asArray("test.xml"), attributes.getStringArray("locations"));
		assertArrayEquals("value", asArray("test.xml"), attributes.getStringArray("value"));

		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
	}
