	@Test
	public void getResourceHttpHeader() throws Exception {
		this.request.setMethod("HEAD");
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.css");
		this.handler.handleRequest(this.request, this.response);

		assertEquals(200, this.response.getStatus());
		assertEquals("text/css", this.response.getContentType());
		assertEquals(17, this.response.getContentLength());
		assertEquals("max-age=3600", this.response.getHeader("Cache-Control"));
		assertTrue(this.response.containsHeader("Last-Modified"));
		assertEquals(this.response.getHeader("Last-Modified"), resourceLastModifiedDate("test/foo.css"));
		assertEquals("bytes", this.response.getHeader("Accept-Ranges"));
		assertEquals(1, this.response.getHeaders("Accept-Ranges").size());
		assertEquals(0, this.response.getContentAsByteArray().length);
	}
