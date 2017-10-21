	@Test
	public void handleMonoWithWildcardBodyTypeAndNullBody() throws Exception {

		MockServerWebExchange exchange = MockServerWebExchange.from(get("/path").build());
		exchange.getAttributes().put(PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, Collections.singleton(APPLICATION_JSON));

		MethodParameter returnType = on(TestController.class).resolveReturnType(Mono.class, ResponseEntity.class);
		HandlerResult result = new HandlerResult(new TestController(), Mono.just(notFound().build()), returnType);

		this.resultHandler.handleResult(exchange, result).block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.NOT_FOUND, exchange.getResponse().getStatusCode());
		assertResponseBodyIsEmpty(exchange);
	}
