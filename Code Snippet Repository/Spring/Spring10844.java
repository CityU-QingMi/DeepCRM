	@Test
	public void resolveMapArgument() throws Exception {
		String name = "foo";
		String value = "bar";
		Map<String, String> expected = Collections.singletonMap(name, value);
		request.addHeader(name, value);

		Object result = resolver.resolveArgument(paramMap, null, webRequest, null);

		assertTrue(result instanceof Map);
		assertEquals("Invalid result", expected, result);
	}
