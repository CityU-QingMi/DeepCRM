	@Test
	public void noHandler() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/does-not-exist").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		Mono<Void> publisher = this.dispatcherHandler.handle(exchange);

		StepVerifier.create(publisher)
				.consumeErrorWith(error -> {
					assertThat(error, instanceOf(ResponseStatusException.class));
					assertThat(error.getMessage(), is("Response status 404 with reason \"No matching handler\""));
				})
				.verify();
	}
