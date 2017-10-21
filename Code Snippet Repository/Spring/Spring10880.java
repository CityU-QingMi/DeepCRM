	@Test
	public void resolveMultipartFileNotAnnot() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		MultipartFile expected = new MockMultipartFile("multipartFileNotAnnot", "Hello World".getBytes());
		request.addFile(expected);
		webRequest = new ServletWebRequest(request);

		MethodParameter param = this.testMethod.annotNotPresent().arg(MultipartFile.class);
		Object result = resolver.resolveArgument(param, null, webRequest, null);
		assertTrue(result instanceof MultipartFile);
		assertEquals("Invalid result", expected, result);
	}
