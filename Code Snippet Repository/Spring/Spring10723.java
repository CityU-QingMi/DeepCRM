	@Test
	public void preflightRequestWithRequestAndMethodHeaderButNoConfig() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(preFlightRequest()
				.header(ACCESS_CONTROL_REQUEST_METHOD, "GET")
				.header(ACCESS_CONTROL_REQUEST_HEADERS, "Header1")
				.build());

		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertFalse(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
