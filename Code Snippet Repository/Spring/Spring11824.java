	@Test
	public void resolveNameFromSystemPropertyThroughPlaceholder() throws Exception {
		String expected = "foo";
		MockServerHttpRequest request = MockServerHttpRequest.get("/").header("bar", expected).build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);

		System.setProperty("systemProperty", "bar");
		try {
			Mono<Object> mono = this.resolver.resolveArgument(
					this.paramResolvedNameWithPlaceholder, this.bindingContext, exchange);

			Object result = mono.block();
			assertTrue(result instanceof String);
			assertEquals(expected, result);
		}
		finally {
			System.clearProperty("systemProperty");
		}
	}
