	@Test
	public void resolveOptionalMultipartFileArgument() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		MultipartFile expected = new MockMultipartFile("optionalMultipartFile", "Hello World".getBytes());
		request.addFile(expected);
		request.addFile(new MockMultipartFile("otherPart", "", "text/plain", "Hello World".getBytes()));
		webRequest = new ServletWebRequest(request);

		Object actualValue = resolver.resolveArgument(optionalMultipartFile, null, webRequest, null);
		assertTrue(actualValue instanceof Optional);
		assertEquals("Invalid result", expected, ((Optional) actualValue).get());

		actualValue = resolver.resolveArgument(optionalMultipartFile, null, webRequest, null);
		assertTrue(actualValue instanceof Optional);
		assertEquals("Invalid result", expected, ((Optional) actualValue).get());
	}
