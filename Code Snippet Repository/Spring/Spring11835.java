	@Test
	public void missingRequestParam() throws Exception {

		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());
		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(String[].class);
		Mono<Object> mono = this.resolver.resolveArgument(param, this.bindContext, exchange);

		StepVerifier.create(mono)
				.expectNextCount(0)
				.expectError(ServerWebInputException.class)
				.verify();
	}
