	@Test
	public void resolvePartArrayArgument() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("multipart/form-data");
		MockPart part1 = new MockPart("requestPart", "Hello World 1".getBytes());
		MockPart part2 = new MockPart("requestPart", "Hello World 2".getBytes());
		request.addPart(part1);
		request.addPart(part2);
		request.addPart(new MockPart("otherPart", "Hello World".getBytes()));
		webRequest = new ServletWebRequest(request);

		Object result = resolver.resolveArgument(paramPartArray, null, webRequest, null);
		assertTrue(result instanceof Part[]);
		Part[] parts = (Part[]) result;
		assertEquals(2, parts.length);
		assertEquals(parts[0], part1);
		assertEquals(parts[1], part2);
	}
