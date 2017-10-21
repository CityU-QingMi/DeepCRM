	@Test
	public void resolveOptionalPartListWithoutMultipartRequest() throws Exception {
		webRequest = new ServletWebRequest(new MockHttpServletRequest());

		Object actualValue = resolver.resolveArgument(optionalPartList, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);

		actualValue = resolver.resolveArgument(optionalPartList, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);
	}
