	@Test
	public void resolveOptionalRequestPartWithoutMultipartRequest() throws Exception {
		webRequest = new ServletWebRequest(new MockHttpServletRequest());

		Object actualValue = resolver.resolveArgument(optionalRequestPart, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);

		actualValue = resolver.resolveArgument(optionalRequestPart, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);
	}
