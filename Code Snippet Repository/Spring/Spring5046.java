	@Test
	public void getMergedAnnotationAttributesWithShadowedAliasComposedAnnotation() {
		Class<?> element = ShadowedAliasComposedContextConfigClass.class;
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, ContextConfig.class);

		String[] expected = asArray("test.xml");

		assertNotNull("Should find @ContextConfig on " + element.getSimpleName(), attributes);
		assertArrayEquals("locations", expected, attributes.getStringArray("locations"));
		assertArrayEquals("value", expected, attributes.getStringArray("value"));
	}
