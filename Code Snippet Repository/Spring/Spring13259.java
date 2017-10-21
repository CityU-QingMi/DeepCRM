	@Test
	public void partialContentByteRange() throws Exception {
		this.request.addHeader("Range", "bytes=0-1");
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.txt");
		this.handler.handleRequest(this.request, this.response);

		assertEquals(206, this.response.getStatus());
		assertEquals("text/plain", this.response.getContentType());
		assertEquals(2, this.response.getContentLength());
		assertEquals("bytes 0-1/10", this.response.getHeader("Content-Range"));
		assertEquals("So", this.response.getContentAsString());
		assertEquals("bytes", this.response.getHeader("Accept-Ranges"));
		assertEquals(1, this.response.getHeaders("Accept-Ranges").size());
	}
