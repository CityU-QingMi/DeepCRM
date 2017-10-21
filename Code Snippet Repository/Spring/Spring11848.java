	private void testHandle(Object returnValue, MethodParameter returnType) {

		MockServerWebExchange exchange = MockServerWebExchange.from(get("/path").build());
		HandlerResult result = handlerResult(returnValue, returnType);
		this.resultHandler.handleResult(exchange, result).block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.OK, exchange.getResponse().getStatusCode());
		assertEquals("text/plain;charset=UTF-8", exchange.getResponse().getHeaders().getFirst("Content-Type"));
		assertResponseBody(exchange, "abc");
	}
