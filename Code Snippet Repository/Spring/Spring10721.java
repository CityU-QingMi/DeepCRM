	@Test
	public void preflightRequestMatchedAllowedMethod() throws Exception {
		MockServerHttpRequest request = preFlightRequest().header(ACCESS_CONTROL_REQUEST_METHOD, "GET").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		this.conf.addAllowedOrigin("*");
		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertNull(response.getStatusCode());
		assertEquals("GET,HEAD", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS));
	}
