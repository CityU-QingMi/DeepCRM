	@Test
	public void resolvePartListArgument() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("multipart/form-data");
		MockPart part1 = new MockPart("requestPart", "Hello World 1".getBytes());
		MockPart part2 = new MockPart("requestPart", "Hello World 2".getBytes());
		request.addPart(part1);
		request.addPart(part2);
		request.addPart(new MockPart("otherPart", "Hello World".getBytes()));
		webRequest = new ServletWebRequest(request);

		Object result = resolver.resolveArgument(paramPartList, null, webRequest, null);
		assertTrue(result instanceof List);
		assertEquals(Arrays.asList(part1, part2), result);
	}
