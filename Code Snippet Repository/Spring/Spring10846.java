	@Test
	public void resolveHttpHeadersArgument() throws Exception {
		String name = "foo";
		String value1 = "bar";
		String value2 = "baz";

		request.addHeader(name, value1);
		request.addHeader(name, value2);

		HttpHeaders expected = new HttpHeaders();
		expected.add(name, value1);
		expected.add(name, value2);

		Object result = resolver.resolveArgument(paramHttpHeaders, null, webRequest, null);

		assertTrue(result instanceof HttpHeaders);
		assertEquals("Invalid result", expected, result);
	}
