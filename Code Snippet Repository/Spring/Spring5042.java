	@Test
	public void getMergedAnnotationAttributesWithImplicitAliasesInMetaAnnotationOnComposedAnnotation() {
		Class<?> element = ComposedImplicitAliasesContextConfigClass.class;
		String name = ImplicitAliasesContextConfig.class.getName();
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, name);
		String[] expected = asArray("A.xml", "B.xml");

		assertNotNull("Should find @ImplicitAliasesContextConfig on " + element.getSimpleName(), attributes);
		assertArrayEquals("groovyScripts", expected, attributes.getStringArray("groovyScripts"));
		assertArrayEquals("xmlFiles", expected, attributes.getStringArray("xmlFiles"));
		assertArrayEquals("locations", expected, attributes.getStringArray("locations"));
		assertArrayEquals("value", expected, attributes.getStringArray("value"));

		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
	}
