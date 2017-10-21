	@Test
	public void matchHeadersCondition() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/foo").header("foo", "bar").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);

		RequestMappingInfo info = paths("/foo").headers("foo=bar").build();
		RequestMappingInfo match = info.getMatchingCondition(exchange);

		assertNotNull(match);

		info = paths("/foo").headers("foo!=bar").build();
		match = info.getMatchingCondition(exchange);

		assertNull(match);
	}
