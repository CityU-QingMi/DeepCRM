	@Test
	public void handleIframeRequest() throws Exception {
		resetResponseAndHandleRequest("GET", "/echo/iframe.html", HttpStatus.OK);

		assertEquals("text/html;charset=UTF-8", this.servletResponse.getContentType());
		assertTrue(this.servletResponse.getContentAsString().startsWith("<!DOCTYPE html>\n"));
		assertEquals(490, this.servletResponse.getContentLength());
		assertEquals("no-store, no-cache, must-revalidate, max-age=0", this.response.getHeaders().getCacheControl());
		assertEquals("\"0096cbd37f2a5218c33bb0826a7c74cbf\"", this.response.getHeaders().getETag());
	}
