	@Test
	public void actualRequestWithOriginHeaderAndAllowedOrigin() throws Exception {
		ServerWebExchange exchange = actualRequest();
		this.conf.addAllowedOrigin("*");
		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertTrue(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals("*", response.getHeaders().getFirst(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertFalse(response.getHeaders().containsKey(HttpHeaders.ACCESS_CONTROL_MAX_AGE));
		assertFalse(response.getHeaders().containsKey(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS));
		assertNull(response.getStatusCode());
	}
