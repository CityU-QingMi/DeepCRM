	@Test
	public void responseEntityHeaders() throws Exception {
		URI location = new URI("/path");
		ResponseEntity<Void> value = ResponseEntity.created(location).build();
		MethodParameter returnType = on(TestController.class).resolveReturnType(entity(Void.class));
		HandlerResult result = handlerResult(value, returnType);
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/path").build());
		this.resultHandler.handleResult(exchange, result).block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.CREATED, exchange.getResponse().getStatusCode());
		assertEquals(1, exchange.getResponse().getHeaders().size());
		assertEquals(location, exchange.getResponse().getHeaders().getLocation());
		assertResponseBodyIsEmpty(exchange);
	}
