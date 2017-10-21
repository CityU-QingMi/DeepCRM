	@Test
	public void getMergedAnnotationAttributesWithAliasedComposedAnnotation() {
		Class<?> element = AliasedComposedContextConfigClass.class;
		String name = ContextConfig.class.getName();
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, name);

		assertNotNull("Should find @ContextConfig on " + element.getSimpleName(), attributes);
		assertArrayEquals("value", asArray("test.xml"), attributes.getStringArray("value"));
		assertArrayEquals("locations", asArray("test.xml"), attributes.getStringArray("locations"));

		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
	}
