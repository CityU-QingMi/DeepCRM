	@Test
	public void resolveOptionalMultipartFileArgumentWithoutMultipartRequest() throws Exception {
		webRequest = new ServletWebRequest(new MockHttpServletRequest());

		Object actualValue = resolver.resolveArgument(optionalMultipartFile, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);

		actualValue = resolver.resolveArgument(optionalMultipartFile, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);
	}
