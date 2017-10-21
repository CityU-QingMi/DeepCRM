	@Test
	public void resolveMultipartFileListNotannot() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		MultipartFile expected1 = new MockMultipartFile("multipartFileList", "Hello World 1".getBytes());
		MultipartFile expected2 = new MockMultipartFile("multipartFileList", "Hello World 2".getBytes());
		request.addFile(expected1);
		request.addFile(expected2);
		webRequest = new ServletWebRequest(request);

		MethodParameter param = this.testMethod
				.annotNotPresent(RequestParam.class).arg(List.class, MultipartFile.class);

		Object result = resolver.resolveArgument(param, null, webRequest, null);
		assertTrue(result instanceof List);
		assertEquals(Arrays.asList(expected1, expected2), result);
	}
