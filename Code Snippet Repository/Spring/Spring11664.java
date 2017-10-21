	@Test
	public void matchParamsCondition() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/foo?foo=bar").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);

		RequestMappingInfo info = paths("/foo").params("foo=bar").build();
		RequestMappingInfo match = info.getMatchingCondition(exchange);

		assertNotNull(match);

		info = paths("/foo").params("foo!=bar").build();
		match = info.getMatchingCondition(exchange);

		assertNull(match);
	}
