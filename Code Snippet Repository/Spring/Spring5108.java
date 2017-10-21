	private void assertAnnotationSynthesisFromMapWithImplicitAliases(String attributeNameAndValue) throws Exception {
		Map<String, Object> map = Collections.singletonMap(attributeNameAndValue, attributeNameAndValue);
		ImplicitAliasesContextConfig config = synthesizeAnnotation(map, ImplicitAliasesContextConfig.class, null);
		assertNotNull(config);
		assertEquals("value: ", attributeNameAndValue, config.value());
		assertEquals("location1: ", attributeNameAndValue, config.location1());
		assertEquals("location2: ", attributeNameAndValue, config.location2());
		assertEquals("location3: ", attributeNameAndValue, config.location3());
		assertEquals("xmlFile: ", attributeNameAndValue, config.xmlFile());
		assertEquals("groovyScript: ", attributeNameAndValue, config.groovyScript());
	}
