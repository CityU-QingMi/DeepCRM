	@Test
	public void resolveStringArgument() throws Exception {
		String expected = "foo";
		ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").header("name", expected).build());

		Mono<Object> mono = this.resolver.resolveArgument(
				this.paramNamedDefaultValueStringHeader, this.bindingContext, exchange);

		Object result = mono.block();
		assertTrue(result instanceof String);
		assertEquals(expected, result);
	}
