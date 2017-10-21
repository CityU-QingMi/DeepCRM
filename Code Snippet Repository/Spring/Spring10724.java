	@Test
	public void preflightRequestValidRequestAndConfig() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(preFlightRequest()
				.header(ACCESS_CONTROL_REQUEST_METHOD, "GET")
				.header(ACCESS_CONTROL_REQUEST_HEADERS, "Header1")
				.build());

		this.conf.addAllowedOrigin("*");
		this.conf.addAllowedMethod("GET");
		this.conf.addAllowedMethod("PUT");
		this.conf.addAllowedHeader("header1");
		this.conf.addAllowedHeader("header2");

		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertTrue(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals("*", response.getHeaders().getFirst(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertTrue(response.getHeaders().containsKey(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS));
		assertEquals("GET,PUT", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS));
		assertFalse(response.getHeaders().containsKey(HttpHeaders.ACCESS_CONTROL_MAX_AGE));
		assertNull(response.getStatusCode());
	}
