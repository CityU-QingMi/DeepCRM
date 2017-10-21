	@Test
	public void resolveOptionalMultipartFileArgumentNotPresent() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		webRequest = new ServletWebRequest(request);

		Object actualValue = resolver.resolveArgument(optionalMultipartFile, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);

		actualValue = resolver.resolveArgument(optionalMultipartFile, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);
	}
