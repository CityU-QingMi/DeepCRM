	@Test
	public void matchProducesCondition() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/foo").accept(MediaType.TEXT_PLAIN).build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);

		RequestMappingInfo info = paths("/foo").produces("text/plain").build();
		RequestMappingInfo match = info.getMatchingCondition(exchange);

		assertNotNull(match);

		info = paths("/foo").produces("application/xml").build();
		match = info.getMatchingCondition(exchange);

		assertNull(match);
	}
