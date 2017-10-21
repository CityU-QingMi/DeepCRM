	@Test
	public void enrichAndValidateAttributesWithImplicitAliases() {
		Map<String, Object> attributes = new HashMap<String, Object>() {{
			put("groovyScript", "groovy!");
		}};

		Map<String, Object> expectedAttributes = new HashMap<String, Object>() {{
			put("groovyScript", "groovy!");
			put("xmlFile", "groovy!");
			put("value", "groovy!");
			put("location1", "groovy!");
			put("location2", "groovy!");
			put("location3", "groovy!");
			put("nonAliasedAttribute", "");
			put("configClass", Object.class);
		}};

		assertEnrichAndValidateAttributes(attributes, expectedAttributes);
	}
