	@Test
	public void actualRequestCaseInsensitiveOriginMatch() throws Exception {
		ServerWebExchange exchange = actualRequest();
		this.conf.addAllowedOrigin("http://DOMAIN2.com");
		this.processor.process(this.conf, exchange);

		ServerHttpResponse response = exchange.getResponse();
		assertTrue(response.getHeaders().containsKey(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertNull(response.getStatusCode());
	}
