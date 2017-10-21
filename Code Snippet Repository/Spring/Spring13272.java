	@Test
	@SuppressWarnings("")
	public void getResourceHttp10BehaviorNoCache() throws Exception {
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.css");
		this.handler.setCacheSeconds(0);
		this.handler.setUseExpiresHeader(true);
		this.handler.setUseCacheControlNoStore(false);
		this.handler.setUseCacheControlHeader(true);
		this.handler.handleRequest(this.request, this.response);

		assertEquals("no-cache", this.response.getHeader("Pragma"));
		assertThat(this.response.getHeaderValues("Cache-Control"), Matchers.iterableWithSize(1));
		assertEquals("no-cache", this.response.getHeader("Cache-Control"));
		assertTrue(dateHeaderAsLong("Expires") <= System.currentTimeMillis());
		assertTrue(this.response.containsHeader("Last-Modified"));
		assertEquals(dateHeaderAsLong("Last-Modified") / 1000, resourceLastModified("test/foo.css") / 1000);
		assertEquals("bytes", this.response.getHeader("Accept-Ranges"));
		assertEquals(1, this.response.getHeaders("Accept-Ranges").size());
	}
