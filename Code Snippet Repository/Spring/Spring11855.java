	@Test
	public void handleResponseEntityWithNullBody() throws Exception {
		Object returnValue = Mono.just(notFound().build());
		MethodParameter type = on(TestController.class).resolveReturnType(Mono.class, entity(String.class));
		HandlerResult result = handlerResult(returnValue, type);
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/path").build());
		this.resultHandler.handleResult(exchange, result).block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.NOT_FOUND, exchange.getResponse().getStatusCode());
		assertResponseBodyIsEmpty(exchange);
	}
