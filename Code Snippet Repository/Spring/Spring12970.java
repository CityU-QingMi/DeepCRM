	@Test
	public void resolveOptionalMultipartFileList() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		MultipartFile expected = new MockMultipartFile("requestPart", "Hello World".getBytes());
		request.addFile(expected);
		request.addFile(new MockMultipartFile("otherPart", "", "text/plain", "Hello World".getBytes()));
		webRequest = new ServletWebRequest(request);

		Object actualValue = resolver.resolveArgument(optionalMultipartFileList, null, webRequest, null);
		assertTrue(actualValue instanceof Optional);
		assertEquals("Invalid result", Collections.singletonList(expected), ((Optional) actualValue).get());

		actualValue = resolver.resolveArgument(optionalMultipartFileList, null, webRequest, null);
		assertTrue(actualValue instanceof Optional);
		assertEquals("Invalid result", Collections.singletonList(expected), ((Optional) actualValue).get());
	}
