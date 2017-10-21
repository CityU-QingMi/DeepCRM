	@Test
	public void resolveMultipartFileArray() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		MultipartFile expected1 = new MockMultipartFile("mfilearray", "Hello World 1".getBytes());
		MultipartFile expected2 = new MockMultipartFile("mfilearray", "Hello World 2".getBytes());
		request.addFile(expected1);
		request.addFile(expected2);
		request.addFile(new MockMultipartFile("other", "Hello World 3".getBytes()));
		webRequest = new ServletWebRequest(request);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(MultipartFile[].class);
		Object result = resolver.resolveArgument(param, null, webRequest, null);

		assertTrue(result instanceof MultipartFile[]);
		MultipartFile[] parts = (MultipartFile[]) result;
		assertEquals(2, parts.length);
		assertEquals(parts[0], expected1);
		assertEquals(parts[1], expected2);
	}
