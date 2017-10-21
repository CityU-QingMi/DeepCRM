	@Test
	public void resolveMultipartFileList() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		MultipartFile expected1 = new MockMultipartFile("mfilelist", "Hello World 1".getBytes());
		MultipartFile expected2 = new MockMultipartFile("mfilelist", "Hello World 2".getBytes());
		request.addFile(expected1);
		request.addFile(expected2);
		request.addFile(new MockMultipartFile("other", "Hello World 3".getBytes()));
		webRequest = new ServletWebRequest(request);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(List.class, MultipartFile.class);
		Object result = resolver.resolveArgument(param, null, webRequest, null);

		assertTrue(result instanceof List);
		assertEquals(Arrays.asList(expected1, expected2), result);
	}
