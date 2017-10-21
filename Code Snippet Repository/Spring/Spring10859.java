	@Test
	public void isMultipartRequestHttpPut() throws Exception {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		MultipartFile expected = new MockMultipartFile("multipartFileList", "Hello World".getBytes());
		request.addFile(expected);
		request.setMethod("PUT");
		webRequest = new ServletWebRequest(request);

		MethodParameter param = this.testMethod
				.annotNotPresent(RequestParam.class).arg(List.class, MultipartFile.class);

		Object actual = resolver.resolveArgument(param, null, webRequest, null);
		assertTrue(actual instanceof List);
		assertEquals(expected, ((List<?>) actual).get(0));
	}
