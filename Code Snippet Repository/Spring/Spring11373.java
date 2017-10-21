	@Test
	public void requestBodyError() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(
				MockServerHttpRequest.post("/request-body").body(Mono.error(EXCEPTION)));
		
		Mono<Void> publisher = this.dispatcherHandler.handle(exchange);

		StepVerifier.create(publisher)
				.consumeErrorWith(error -> assertSame(EXCEPTION, error))
				.verify();
	}
