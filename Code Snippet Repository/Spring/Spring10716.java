	@Test
	public void preflightRequestWithNullConfig() throws Exception {
		MockServerHttpRequest request = preFlightRequest().header(ACCESS_CONTROL_REQUEST_METHOD, "GET").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		this.conf.addAllowedOrigin("*");
		this.processor.process(null, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertFalse(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
