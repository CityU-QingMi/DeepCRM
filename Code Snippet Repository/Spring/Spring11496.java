	@Test
	public void attribute() throws Exception {
		MockServerHttpRequest mockRequest = MockServerHttpRequest.method(HttpMethod.GET, "http://example.com").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(mockRequest);
		exchange.getAttributes().put("foo", "bar");

		DefaultServerRequest request = new DefaultServerRequest(exchange, messageReaders);

		assertEquals(Optional.of("bar"), request.attribute("foo"));
	}
