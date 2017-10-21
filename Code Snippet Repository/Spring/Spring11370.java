	@Test
	public void controllerThrowsException() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/raise-exception").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		Mono<Void> publisher = this.dispatcherHandler.handle(exchange);

		StepVerifier.create(publisher)
				.consumeErrorWith(error -> assertSame(EXCEPTION, error))
				.verify();
	}
