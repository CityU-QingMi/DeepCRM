	@Test
	public void resolveDefaultValue() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());
		Mono<Object> mono = this.resolver.resolveArgument(
				this.paramNamedDefaultValueStringHeader, this.bindingContext, exchange);

		Object result = mono.block();
		assertTrue(result instanceof String);
		assertEquals("bar", result);
	}
