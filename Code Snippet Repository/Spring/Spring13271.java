	@Test
	@SuppressWarnings("")
	public void getResourceHttp10BehaviorCache() throws Exception {
		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.css");
		this.handler.setCacheSeconds(3600);
		this.handler.setUseExpiresHeader(true);
		this.handler.setUseCacheControlHeader(true);
		this.handler.setAlwaysMustRevalidate(true);
		this.handler.handleRequest(this.request, this.response);

		assertEquals("max-age=3600, must-revalidate", this.response.getHeader("Cache-Control"));
		assertTrue(dateHeaderAsLong("Expires") >= System.currentTimeMillis() - 1000 + (3600 * 1000));
		assertTrue(this.response.containsHeader("Last-Modified"));
		assertEquals(this.response.getHeader("Last-Modified"), resourceLastModifiedDate("test/foo.css"));
		assertEquals("bytes", this.response.getHeader("Accept-Ranges"));
		assertEquals(1, this.response.getHeaders("Accept-Ranges").size());
	}
