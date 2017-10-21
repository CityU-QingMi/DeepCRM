	@Test
	public void matchConsumesCondition() {
		MockServerHttpRequest request = MockServerHttpRequest.post("/foo").contentType(MediaType.TEXT_PLAIN).build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);

		RequestMappingInfo info = paths("/foo").consumes("text/plain").build();
		RequestMappingInfo match = info.getMatchingCondition(exchange);

		assertNotNull(match);

		info = paths("/foo").consumes("application/xml").build();
		match = info.getMatchingCondition(exchange);

		assertNull(match);
	}
