	@Test
	public void resolveOptionalMultipartFileListWithoutMultipartRequest() throws Exception {
		webRequest = new ServletWebRequest(new MockHttpServletRequest());

		Object actualValue = resolver.resolveArgument(optionalMultipartFileList, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);

		actualValue = resolver.resolveArgument(optionalMultipartFileList, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);
	}
