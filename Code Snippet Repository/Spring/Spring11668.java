	@Test
	public void matchCustomCondition() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/foo?foo=bar").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);

		RequestMappingInfo info = paths("/foo").params("foo=bar").build();
		RequestMappingInfo match = info.getMatchingCondition(exchange);

		assertNotNull(match);

		info = paths("/foo").params("foo!=bar")
				.customCondition(new ParamsRequestCondition("foo!=bar")).build();

		match = info.getMatchingCondition(exchange);

		assertNull(match);
	}
