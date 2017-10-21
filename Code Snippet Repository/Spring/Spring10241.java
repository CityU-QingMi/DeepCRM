	private void testPathSegment(String rawValue, String valueToMatch, MultiValueMap<String, String> params) {

		PathContainer container = PathContainer.parsePath(rawValue);

		if ("".equals(rawValue)) {
			assertEquals(0, container.elements().size());
			return;
		}

		assertEquals(1, container.elements().size());
		PathSegment segment = (PathSegment) container.elements().get(0);

		assertEquals("value: '" + rawValue + "'", rawValue, segment.value());
		assertEquals("valueToMatch: '" + rawValue + "'", valueToMatch, segment.valueToMatch());
		assertEquals("params: '" + rawValue + "'", params, segment.parameters());
	}
