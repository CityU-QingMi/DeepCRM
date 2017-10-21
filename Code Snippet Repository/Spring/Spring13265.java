	@Test
	public void partialContentInvalidRangeHeader() throws Exception {
		this.request.addHeader("Range", "bytes= foo bar");
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.txt");
		this.handler.handleRequest(this.request, this.response);

		assertEquals(416, this.response.getStatus());
		assertEquals("bytes */10", this.response.getHeader("Content-Range"));
		assertEquals("bytes", this.response.getHeader("Accept-Ranges"));
		assertEquals(1, this.response.getHeaders("Accept-Ranges").size());
	}
