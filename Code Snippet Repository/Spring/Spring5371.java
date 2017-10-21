	@Test
	public void extractUriTemplateVariables() throws Exception {
		Map<String, String> result = pathMatcher.extractUriTemplateVariables("/hotels/{hotel}", "/hotels/1");
		assertEquals(Collections.singletonMap("hotel", "1"), result);

		result = pathMatcher.extractUriTemplateVariables("/h?tels/{hotel}", "/hotels/1");
		assertEquals(Collections.singletonMap("hotel", "1"), result);

		result = pathMatcher.extractUriTemplateVariables("/hotels/{hotel}/bookings/{booking}", "/hotels/1/bookings/2");
		Map<String, String> expected = new LinkedHashMap<>();
		expected.put("hotel", "1");
		expected.put("booking", "2");
		assertEquals(expected, result);

		result = pathMatcher.extractUriTemplateVariables("/**/hotels/**/{hotel}", "/foo/hotels/bar/1");
		assertEquals(Collections.singletonMap("hotel", "1"), result);

		result = pathMatcher.extractUriTemplateVariables("/{page}.html", "/42.html");
		assertEquals(Collections.singletonMap("page", "42"), result);

		result = pathMatcher.extractUriTemplateVariables("/{page}.*", "/42.html");
		assertEquals(Collections.singletonMap("page", "42"), result);

		result = pathMatcher.extractUriTemplateVariables("/A-{B}-C", "/A-b-C");
		assertEquals(Collections.singletonMap("B", "b"), result);

		result = pathMatcher.extractUriTemplateVariables("/{name}.{extension}", "/test.html");
		expected = new LinkedHashMap<>();
		expected.put("name", "test");
		expected.put("extension", "html");
		assertEquals(expected, result);
	}
