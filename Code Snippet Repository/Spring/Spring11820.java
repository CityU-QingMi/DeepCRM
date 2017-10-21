	@Test
	public void resolveStringArrayArgument() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/").header("name", "foo", "bar").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);

		Mono<Object> mono = this.resolver.resolveArgument(
				this.paramNamedValueStringArray, this.bindingContext, exchange);

		Object result = mono.block();
		assertTrue(result instanceof String[]);
		assertArrayEquals(new String[] {"foo", "bar"}, (String[]) result);
	}
