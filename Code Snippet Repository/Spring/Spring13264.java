	@Test
	public void partialContentSuffixRangeLargeSuffix() throws Exception {
		this.request.addHeader("Range", "bytes=-11");
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.txt");
		this.handler.handleRequest(this.request, this.response);

		assertEquals(206, this.response.getStatus());
		assertEquals("text/plain", this.response.getContentType());
		assertEquals(10, this.response.getContentLength());
		assertEquals("bytes 0-9/10", this.response.getHeader("Content-Range"));
		assertEquals("Some text.", this.response.getContentAsString());
		assertEquals("bytes", this.response.getHeader("Accept-Ranges"));
		assertEquals(1, this.response.getHeaders("Accept-Ranges").size());
	}
