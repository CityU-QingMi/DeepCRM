	@Test
	public void responseBodyMessageConversionError() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(
				MockServerHttpRequest.post("/request-body").accept(APPLICATION_JSON).body("body"));

		Mono<Void> publisher = this.dispatcherHandler.handle(exchange);

		StepVerifier.create(publisher)
				.consumeErrorWith(error -> assertThat(error, instanceOf(NotAcceptableStatusException.class)))
				.verify();
	}
