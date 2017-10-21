	@SuppressWarnings("")
	private void assertEnrichAndValidateAttributes(Map<String, Object> sourceAttributes, Map<String, Object> expected) {
		Class<? extends Annotation> annotationType = ImplicitAliasesContextConfig.class;

		// Since the ordering of attribute methods returned by the JVM is
		// non-deterministic, we have to rig the attributeAliasesCache in AnnotationUtils
		// so that the tests consistently fail in case enrichAndValidateAttributes() is
		// buggy.
		//
		// Otherwise, these tests would intermittently pass even for an invalid
		// implementation.
		Map<Class<? extends Annotation>, MultiValueMap<String, String>> attributeAliasesCache =
				(Map<Class<? extends Annotation>, MultiValueMap<String, String>>) AnnotationUtilsTests.getCache("attributeAliasesCache");

		// Declare aliases in an order that will cause enrichAndValidateAttributes() to
		// fail unless it considers all aliases in the set of implicit aliases.
		MultiValueMap<String, String> aliases = new LinkedMultiValueMap<>();
		aliases.put("xmlFile", Arrays.asList("value", "groovyScript", "location1", "location2", "location3"));
		aliases.put("groovyScript", Arrays.asList("value", "xmlFile", "location1", "location2", "location3"));
		aliases.put("value", Arrays.asList("xmlFile", "groovyScript", "location1", "location2", "location3"));
		aliases.put("location1", Arrays.asList("xmlFile", "groovyScript", "value", "location2", "location3"));
		aliases.put("location2", Arrays.asList("xmlFile", "groovyScript", "value", "location1", "location3"));
		aliases.put("location3", Arrays.asList("xmlFile", "groovyScript", "value", "location1", "location2"));

		attributeAliasesCache.put(annotationType, aliases);

		MapAnnotationAttributeExtractor extractor = new MapAnnotationAttributeExtractor(sourceAttributes, annotationType, null);
		Map<String, Object> enriched = extractor.getSource();

		assertEquals("attribute map size", expected.size(), enriched.size());
		expected.forEach((attr, expectedValue) -> assertThat("for attribute '" + attr + "'", enriched.get(attr), is(expectedValue)));
	}
