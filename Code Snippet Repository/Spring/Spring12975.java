	@Test
	public void resolveOptionalPartArgumentWithoutMultipartRequest() throws Exception {
		webRequest = new ServletWebRequest(new MockHttpServletRequest());

		Object actualValue = resolver.resolveArgument(optionalPart, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);

		actualValue = resolver.resolveArgument(optionalPart, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);
	}
