	@Test
	public void getResourceFromAlternatePath() throws Exception {
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "baz.css");
		this.handler.handleRequest(this.request, this.response);

		assertEquals("text/css", this.response.getContentType());
		assertEquals(17, this.response.getContentLength());
		assertEquals("max-age=3600", this.response.getHeader("Cache-Control"));
		assertTrue(this.response.containsHeader("Last-Modified"));
		assertEquals(this.response.getHeader("Last-Modified"), resourceLastModifiedDate("testalternatepath/baz.css"));
		assertEquals("bytes", this.response.getHeader("Accept-Ranges"));
		assertEquals(1, this.response.getHeaders("Accept-Ranges").size());
		assertEquals("h1 { color:red; }", this.response.getContentAsString());
	}
