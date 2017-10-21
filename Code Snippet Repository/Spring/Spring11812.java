	@Test
	public void resolveMultiValueMapArgument() throws Exception {
		String name = "foo";
		String value1 = "bar";
		String value2 = "baz";
		MockServerHttpRequest request = MockServerHttpRequest.get("/").header(name, value1, value2).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		MultiValueMap<String, String> expected = new LinkedMultiValueMap<>(1);
		expected.add(name, value1);
		expected.add(name, value2);

		Mono<Object> mono = resolver.resolveArgument(paramMultiValueMap, null, exchange);
		Object result = mono.block();

		assertTrue(result instanceof MultiValueMap);
		assertEquals("Invalid result", expected, result);
	}
