	@Test
	public void handleMonoWithWildcardBodyType() throws Exception {

		MockServerWebExchange exchange = MockServerWebExchange.from(get("/path").build());
		exchange.getAttributes().put(PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, Collections.singleton(APPLICATION_JSON));

		MethodParameter type = on(TestController.class).resolveReturnType(Mono.class, ResponseEntity.class);
		HandlerResult result = new HandlerResult(new TestController(), Mono.just(ok().body("body")), type);

		this.resultHandler.handleResult(exchange, result).block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.OK, exchange.getResponse().getStatusCode());
		assertResponseBody(exchange, "body");
	}
