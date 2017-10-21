	@Test
	public void preflightRequestWithoutRequestMethod() throws Exception {
		MockServerHttpRequest request = preFlightRequest().header(ACCESS_CONTROL_REQUEST_HEADERS, "Header1").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertFalse(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
