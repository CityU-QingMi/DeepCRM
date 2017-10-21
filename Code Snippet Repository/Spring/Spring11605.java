	@Test
	public void partialContentByteRange() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("").header("Range", "bytes=0-1").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		setPathWithinHandlerMapping(exchange, "foo.txt");
		this.handler.handle(exchange).block(TIMEOUT);

		assertEquals(HttpStatus.PARTIAL_CONTENT, exchange.getResponse().getStatusCode());
		assertEquals(MediaType.TEXT_PLAIN, exchange.getResponse().getHeaders().getContentType());
		assertEquals(2, exchange.getResponse().getHeaders().getContentLength());
		assertEquals("bytes 0-1/10", exchange.getResponse().getHeaders().getFirst("Content-Range"));
		assertEquals("bytes", exchange.getResponse().getHeaders().getFirst("Accept-Ranges"));
		assertEquals(1, exchange.getResponse().getHeaders().get("Accept-Ranges").size());
		assertResponseBody(exchange, "So");
	}
