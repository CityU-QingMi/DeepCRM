	@Test
	public void resolvePartArray() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockPart expected1 = new MockPart("pfilearray", "Hello World 1".getBytes());
		MockPart expected2 = new MockPart("pfilearray", "Hello World 2".getBytes());
		request.setMethod("POST");
		request.setContentType("multipart/form-data");
		request.addPart(expected1);
		request.addPart(expected2);
		request.addPart(new MockPart("other", "Hello World 3".getBytes()));
		webRequest = new ServletWebRequest(request);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(Part[].class);
		Object result = resolver.resolveArgument(param, null, webRequest, null);

		assertTrue(result instanceof Part[]);
		Part[] parts = (Part[]) result;
		assertEquals(2, parts.length);
		assertEquals(parts[0], expected1);
		assertEquals(parts[1], expected2);
	}
