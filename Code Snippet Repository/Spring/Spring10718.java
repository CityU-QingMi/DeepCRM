	@Test
	public void actualRequestCredentialsWithOriginWildcard() throws Exception {
		ServerWebExchange exchange = actualRequest();
		this.conf.addAllowedOrigin("*");
		this.conf.setAllowCredentials(true);
		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertTrue(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals("http://domain2.com", response.getHeaders().getFirst(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertTrue(response.getHeaders().containsKey(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS));
		assertEquals("true", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS));
		assertNull(response.getStatusCode());
	}
