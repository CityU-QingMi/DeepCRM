	private void assertGetAttributeValueForImplicitAliases(Class<?> clazz, String expected) throws Exception {
		Method xmlFile = ImplicitAliasesContextConfig.class.getDeclaredMethod("xmlFile");
		Method groovyScript = ImplicitAliasesContextConfig.class.getDeclaredMethod("groovyScript");
		Method value = ImplicitAliasesContextConfig.class.getDeclaredMethod("value");

		AnnotationAttributeExtractor<?> extractor = createExtractorFor(clazz, expected, ImplicitAliasesContextConfig.class);

		assertThat(extractor.getAttributeValue(value), is(expected));
		assertThat(extractor.getAttributeValue(groovyScript), is(expected));
		assertThat(extractor.getAttributeValue(xmlFile), is(expected));
	}
