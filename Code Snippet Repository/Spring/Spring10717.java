	@Test
	public void actualRequestCredentials() throws Exception {
		ServerWebExchange exchange = actualRequest();
		this.conf.addAllowedOrigin("http://domain1.com");
		this.conf.addAllowedOrigin("http://domain2.com");
		this.conf.addAllowedOrigin("http://domain3.com");
		this.conf.setAllowCredentials(true);
		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertTrue(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals("http://domain2.com", response.getHeaders().getFirst(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertTrue(response.getHeaders().containsKey(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS));
		assertEquals("true", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS));
		assertNull(response.getStatusCode());
	}
