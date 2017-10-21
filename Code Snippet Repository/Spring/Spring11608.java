	@Test
	public void getResource() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("").build());
		setPathWithinHandlerMapping(exchange, "foo.css");
		this.handler.handle(exchange).block(TIMEOUT);

		HttpHeaders headers = exchange.getResponse().getHeaders();
		assertEquals(MediaType.parseMediaType("text/css"), headers.getContentType());
		assertEquals(17, headers.getContentLength());
		assertEquals("max-age=3600", headers.getCacheControl());
		assertTrue(headers.containsKey("Last-Modified"));
		assertEquals(headers.getLastModified() / 1000, resourceLastModifiedDate("test/foo.css") / 1000);
		assertEquals("bytes", headers.getFirst("Accept-Ranges"));
		assertEquals(1, headers.get("Accept-Ranges").size());
		assertResponseBody(exchange, "h1 { color:red; }");
	}
