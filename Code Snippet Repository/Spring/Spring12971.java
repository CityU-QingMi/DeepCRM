	@Test
	public void resolveOptionalMultipartFileListNotPresent() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		webRequest = new ServletWebRequest(request);

		Object actualValue = resolver.resolveArgument(optionalMultipartFileList, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);

		actualValue = resolver.resolveArgument(optionalMultipartFileList, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);
	}
