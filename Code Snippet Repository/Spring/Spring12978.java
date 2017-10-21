	@Test
	public void resolveOptionalPartListNotPresent() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("multipart/form-data");
		webRequest = new ServletWebRequest(request);

		Object actualValue = resolver.resolveArgument(optionalPartList, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);

		actualValue = resolver.resolveArgument(optionalPartList, null, webRequest, null);
		assertEquals("Invalid argument value", Optional.empty(), actualValue);
	}
