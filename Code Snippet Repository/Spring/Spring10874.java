	@Test
	public void resolveMultipartFile() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		MultipartFile expected = new MockMultipartFile("mfile", "Hello World".getBytes());
		request.addFile(expected);
		webRequest = new ServletWebRequest(request);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(MultipartFile.class);
		Object result = resolver.resolveArgument(param, null, webRequest, null);
		assertTrue(result instanceof MultipartFile);
		assertEquals("Invalid result", expected, result);
	}
