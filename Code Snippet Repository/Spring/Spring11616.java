	@Test
	public void getResourceWithHtmlMediaType() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("").build());
		setPathWithinHandlerMapping(exchange, "foo.html");
		this.handler.handle(exchange).block(TIMEOUT);

		HttpHeaders headers = exchange.getResponse().getHeaders();
		assertEquals(MediaType.TEXT_HTML, headers.getContentType());
		assertEquals("max-age=3600", headers.getCacheControl());
		assertTrue(headers.containsKey("Last-Modified"));
		assertEquals(headers.getLastModified() / 1000, resourceLastModifiedDate("test/foo.html") / 1000);
		assertEquals("bytes", headers.getFirst("Accept-Ranges"));
		assertEquals(1, headers.get("Accept-Ranges").size());
	}
