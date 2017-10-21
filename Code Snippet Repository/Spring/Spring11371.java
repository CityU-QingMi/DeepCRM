	@Test
	public void unknownReturnType() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/unknown-return-type").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		Mono<Void> publisher = this.dispatcherHandler.handle(exchange);

		StepVerifier.create(publisher)
				.consumeErrorWith(error -> {
					assertThat(error, instanceOf(IllegalStateException.class));
					assertThat(error.getMessage(), startsWith("No HandlerResultHandler"));
				})
				.verify();
	}
