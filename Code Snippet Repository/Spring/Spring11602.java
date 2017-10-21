	private void testInvalidPath(HttpMethod httpMethod, String requestPath, Resource location) throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.method(httpMethod, "").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		setPathWithinHandlerMapping(exchange, requestPath);
		this.handler.handle(exchange).block(TIMEOUT);
		if (!location.createRelative(requestPath).exists() && !requestPath.contains(":")) {
			fail(requestPath + " doesn't actually exist as a relative path");
		}
		assertEquals(HttpStatus.NOT_FOUND, exchange.getResponse().getStatusCode());
	}
