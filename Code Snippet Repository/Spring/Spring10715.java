	@Test
	public void preflightRequestWithEmptyHeaders() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(preFlightRequest()
				.header(ACCESS_CONTROL_REQUEST_METHOD, "GET")
				.header(ACCESS_CONTROL_REQUEST_HEADERS, "")
				.build());

		this.conf.addAllowedHeader("*");
		this.conf.addAllowedOrigin("http://domain2.com");

		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertTrue(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertFalse(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_HEADERS));
		assertNull(response.getStatusCode());
	}
