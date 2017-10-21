	@Test
	public void resolveMultiValueMapArgument() throws Exception {
		String name = "foo";
		String value1 = "bar";
		String value2 = "baz";

		request.addHeader(name, value1);
		request.addHeader(name, value2);

		MultiValueMap<String, String> expected = new LinkedMultiValueMap<>(1);
		expected.add(name, value1);
		expected.add(name, value2);

		Object result = resolver.resolveArgument(paramMultiValueMap, null, webRequest, null);

		assertTrue(result instanceof MultiValueMap);
		assertEquals("Invalid result", expected, result);
	}
