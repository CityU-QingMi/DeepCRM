	@Test
	public void resolveHttpHeadersArgument() throws Exception {
		String name = "foo";
		String value1 = "bar";
		String value2 = "baz";
		MockServerHttpRequest request = MockServerHttpRequest.get("/").header(name, value1, value2).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		HttpHeaders expected = new HttpHeaders();
		expected.add(name, value1);
		expected.add(name, value2);

		Mono<Object> mono = resolver.resolveArgument(paramHttpHeaders, null, exchange);
		Object result = mono.block();

		assertTrue(result instanceof HttpHeaders);
		assertEquals("Invalid result", expected, result);
	}
