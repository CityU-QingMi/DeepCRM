	@Test
	@Ignore
	public void preFlightRequest() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.options("/foo")
				.header("Origin", "http://domain.com")
				.header(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "POST")
				.build());

		RequestMappingInfo info = paths("/foo").methods(RequestMethod.POST).build();
		RequestMappingInfo match = info.getMatchingCondition(exchange);
		assertNotNull(match);

		info = paths("/foo").methods(RequestMethod.OPTIONS).build();
		match = info.getMatchingCondition(exchange);
		assertNull("Pre-flight should match the ACCESS_CONTROL_REQUEST_METHOD", match);
	}
