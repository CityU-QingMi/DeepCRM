	@Test
	public void resolveOptionalRequestPartNotPresent() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("multipart/form-data");
		webRequest = new ServletWebRequest(request);

		Object actualValue = resolver.resolveArgument(optionalRequestPart, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);

		actualValue = resolver.resolveArgument(optionalRequestPart, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);
	}
