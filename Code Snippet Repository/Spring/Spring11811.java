	@Test
	public void resolveMapArgument() throws Exception {
		String name = "foo";
		String value = "bar";
		Map<String, String> expected = Collections.singletonMap(name, value);
		MockServerHttpRequest request = MockServerHttpRequest.get("/").header(name, value).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		Mono<Object> mono = resolver.resolveArgument(paramMap, null, exchange);
		Object result = mono.block();

		assertTrue(result instanceof Map);
		assertEquals("Invalid result", expected, result);
	}
