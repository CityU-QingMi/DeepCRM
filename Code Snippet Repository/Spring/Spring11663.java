	@Test
	public void matchPatternsCondition() {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/foo").build());

		RequestMappingInfo info = paths("/foo*", "/bar").build();
		RequestMappingInfo expected = paths("/foo*").build();

		assertEquals(expected, info.getMatchingCondition(exchange));

		info = paths("/**", "/foo*", "/foo").build();
		expected = paths("/foo", "/foo*", "/**").build();

		assertEquals(expected, info.getMatchingCondition(exchange));
	}
