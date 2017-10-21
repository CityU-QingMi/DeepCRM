	@Test
	public void enrichAndValidateAttributesWithSingleElementThatOverridesAnArray() {
		// @formatter:off
		Map<String, Object> attributes = new HashMap<String, Object>() {{
			// Intentionally storing 'value' as a single String instead of an array.
			// put("value", asArray("/foo"));
			put("value", "/foo");
			put("name", "test");
		}};

		Map<String, Object> expected = new HashMap<String, Object>() {{
			put("value", asArray("/foo"));
			put("path", asArray("/foo"));
			put("name", "test");
			put("method", new RequestMethod[0]);
		}};
		// @formatter:on

		MapAnnotationAttributeExtractor extractor = new MapAnnotationAttributeExtractor(attributes, WebMapping.class, null);
		Map<String, Object> enriched = extractor.getSource();

		assertEquals("attribute map size", expected.size(), enriched.size());
		expected.forEach((attr, expectedValue) -> assertThat("for attribute '" + attr + "'", enriched.get(attr), is(expectedValue)));
	}
