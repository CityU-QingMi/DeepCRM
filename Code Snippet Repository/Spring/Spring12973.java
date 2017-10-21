	@Test
	public void resolveOptionalPartArgument() throws Exception {
		MockPart expected = new MockPart("optionalPart", "Hello World".getBytes());
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("multipart/form-data");
		request.addPart(expected);
		request.addPart(new MockPart("otherPart", "Hello World".getBytes()));
		webRequest = new ServletWebRequest(request);

		Object actualValue = resolver.resolveArgument(optionalPart, null, webRequest, null);
		assertTrue(actualValue instanceof Optional);
		assertEquals("Invalid result", expected, ((Optional) actualValue).get());

		actualValue = resolver.resolveArgument(optionalPart, null, webRequest, null);
		assertTrue(actualValue instanceof Optional);
		assertEquals("Invalid result", expected, ((Optional) actualValue).get());
	}
