	@Test
	public void resolvePartArgument() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("multipart/form-data");
		MockPart expected = new MockPart("part", "Hello World".getBytes());
		request.addPart(expected);
		request.addPart(new MockPart("otherPart", "Hello World".getBytes()));
		webRequest = new ServletWebRequest(request);

		Object result = resolver.resolveArgument(paramPart, null, webRequest, null);
		assertTrue(result instanceof Part);
		assertEquals("Invalid result", expected, result);
	}
