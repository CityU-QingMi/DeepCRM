	@Test
	public void controllerReturnsMonoError() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/error-signal").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		Mono<Void> publisher = this.dispatcherHandler.handle(exchange);

		StepVerifier.create(publisher)
				.consumeErrorWith(error -> assertSame(EXCEPTION, error))
				.verify();
	}
