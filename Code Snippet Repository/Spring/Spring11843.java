	@Test
	public void handleReturnValueEtagInvalidIfNoneMatch() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/path").ifNoneMatch("unquoted").build());

		ResponseEntity<String> entity = ok().eTag("\"deadb33f8badf00d\"").body("body");
		MethodParameter returnType = on(TestController.class).resolveReturnType(entity(String.class));
		HandlerResult result = handlerResult(entity, returnType);
		this.resultHandler.handleResult(exchange, result).block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.OK, exchange.getResponse().getStatusCode());
		assertResponseBody(exchange, "body");
	}
