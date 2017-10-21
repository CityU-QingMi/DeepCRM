	@Test
	public void getResourceNoCache() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("").build());
		setPathWithinHandlerMapping(exchange, "foo.css");
		this.handler.setCacheControl(CacheControl.noStore());
		this.handler.handle(exchange).block(TIMEOUT);

		MockServerHttpResponse response = exchange.getResponse();
		assertEquals("no-store", response.getHeaders().getCacheControl());
		assertTrue(response.getHeaders().containsKey("Last-Modified"));
		assertEquals(response.getHeaders().getLastModified() / 1000, resourceLastModifiedDate("test/foo.css") / 1000);
		assertEquals("bytes", response.getHeaders().getFirst("Accept-Ranges"));
		assertEquals(1, response.getHeaders().get("Accept-Ranges").size());
	}
