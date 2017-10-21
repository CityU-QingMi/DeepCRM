	@Test
	public void notFound() throws Exception {
		Mono<Object> mono = resolver.resolveArgument(
				this.paramNamedValueStringArray, this.bindingContext,
				MockServerWebExchange.from(MockServerHttpRequest.get("/").build()));

		StepVerifier.create(mono)
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();
	}
