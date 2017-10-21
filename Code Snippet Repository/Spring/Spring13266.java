	@Test
	public void partialContentMultipleByteRanges() throws Exception {
		this.request.addHeader("Range", "bytes=0-1, 4-5, 8-9");
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.txt");
		this.handler.handleRequest(this.request, this.response);

		assertEquals(206, this.response.getStatus());
		assertTrue(this.response.getContentType().startsWith("multipart/byteranges; boundary="));

		String boundary = "--" + this.response.getContentType().substring(31);

		String content = this.response.getContentAsString();
		String[] ranges = StringUtils.tokenizeToStringArray(content, "\r\n", false, true);

		assertEquals(boundary, ranges[0]);
		assertEquals("Content-Type: text/plain", ranges[1]);
		assertEquals("Content-Range: bytes 0-1/10", ranges[2]);
		assertEquals("So", ranges[3]);

		assertEquals(boundary, ranges[4]);
		assertEquals("Content-Type: text/plain", ranges[5]);
		assertEquals("Content-Range: bytes 4-5/10", ranges[6]);
		assertEquals(" t", ranges[7]);

		assertEquals(boundary, ranges[8]);
		assertEquals("Content-Type: text/plain", ranges[9]);
		assertEquals("Content-Range: bytes 8-9/10", ranges[10]);
		assertEquals("t.", ranges[11]);
	}
