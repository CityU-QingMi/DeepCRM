	@Test
	public void responseEntityStatusCode() throws Exception {
		ResponseEntity<Void> value = ResponseEntity.noContent().build();
		MethodParameter returnType = on(TestController.class).resolveReturnType(entity(Void.class));
		HandlerResult result = handlerResult(value, returnType);
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/path").build());
		this.resultHandler.handleResult(exchange, result).block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.NO_CONTENT, exchange.getResponse().getStatusCode());
		assertEquals(0, exchange.getResponse().getHeaders().size());
		assertResponseBodyIsEmpty(exchange);
	}
