	@Test
	public void partialContentByteRangeLargeEnd() throws Exception {
		this.request.addHeader("Range", "bytes=9-10000");
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.txt");
		this.handler.handleRequest(this.request, this.response);

		assertEquals(206, this.response.getStatus());
		assertEquals("text/plain", this.response.getContentType());
		assertEquals(1, this.response.getContentLength());
		assertEquals("bytes 9-9/10", this.response.getHeader("Content-Range"));
		assertEquals(".", this.response.getContentAsString());
		assertEquals("bytes", this.response.getHeader("Accept-Ranges"));
		assertEquals(1, this.response.getHeaders("Accept-Ranges").size());
	}
