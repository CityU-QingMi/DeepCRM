	@Test
	public void modified() throws Exception {
		long timestamp = resourceLastModified("test/foo.css") / 1000 * 1000 - 1;
		MockServerHttpRequest request = MockServerHttpRequest.get("").ifModifiedSince(timestamp).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		setPathWithinHandlerMapping(exchange, "foo.css");
		this.handler.handle(exchange).block(TIMEOUT);

		assertNull(exchange.getResponse().getStatusCode());
		assertResponseBody(exchange, "h1 { color:red; }");
	}
