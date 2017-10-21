	@Test
	public void enrichAndValidateAttributesWithImplicitAliasesAndMinimalAttributes() {
		Map<String, Object> attributes = new HashMap<>();
		Map<String, Object> expectedAttributes = new HashMap<String, Object>() {{
			put("groovyScript", "");
			put("xmlFile", "");
			put("value", "");
			put("location1", "");
			put("location2", "");
			put("location3", "");
			put("nonAliasedAttribute", "");
			put("configClass", Object.class);
		}};

		assertEnrichAndValidateAttributes(attributes, expectedAttributes);
	}
