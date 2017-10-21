	@Test
	public void getMergedAnnotationWithImplicitAliasesInMetaAnnotationOnComposedAnnotation() {
		Class<?> element = ComposedImplicitAliasesContextConfigClass.class;
		String name = ImplicitAliasesContextConfig.class.getName();
		ImplicitAliasesContextConfig config = getMergedAnnotation(element, ImplicitAliasesContextConfig.class);
		String[] expected = asArray("A.xml", "B.xml");

		assertNotNull("Should find @ImplicitAliasesContextConfig on " + element.getSimpleName(), config);
		assertArrayEquals("groovyScripts", expected, config.groovyScripts());
		assertArrayEquals("xmlFiles", expected, config.xmlFiles());
		assertArrayEquals("locations", expected, config.locations());
		assertArrayEquals("value", expected, config.value());

		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
	}
