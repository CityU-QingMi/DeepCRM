	@Test
	public void getResourceWithHtmlMediaType() throws Exception {
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.html");
		this.handler.handleRequest(this.request, this.response);

		assertEquals("text/html", this.response.getContentType());
		assertEquals("max-age=3600", this.response.getHeader("Cache-Control"));
		assertTrue(this.response.containsHeader("Last-Modified"));
		assertEquals(this.response.getHeader("Last-Modified"), resourceLastModifiedDate("test/foo.html"));
		assertEquals("bytes", this.response.getHeader("Accept-Ranges"));
		assertEquals(1, this.response.getHeaders("Accept-Ranges").size());
	}
