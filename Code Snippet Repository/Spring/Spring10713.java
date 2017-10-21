	@Test
	public void preflightRequestAllowedHeaders() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(preFlightRequest()
				.header(ACCESS_CONTROL_REQUEST_METHOD, "GET")
				.header(ACCESS_CONTROL_REQUEST_HEADERS, "Header1, Header2")
				.build());

		this.conf.addAllowedHeader("Header1");
		this.conf.addAllowedHeader("Header2");
		this.conf.addAllowedHeader("Header3");
		this.conf.addAllowedOrigin("http://domain2.com");

		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertTrue(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertTrue(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_HEADERS));
		assertTrue(response.getHeaders().getFirst(ACCESS_CONTROL_ALLOW_HEADERS).contains("Header1"));
		assertTrue(response.getHeaders().getFirst(ACCESS_CONTROL_ALLOW_HEADERS).contains("Header2"));
		assertFalse(response.getHeaders().getFirst(ACCESS_CONTROL_ALLOW_HEADERS).contains("Header3"));
		assertNull(response.getStatusCode());
	}
