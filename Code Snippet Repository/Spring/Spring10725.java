	@Test
	public void registerAndMatch() {
		CorsConfiguration config = new CorsConfiguration();
		this.configSource.registerCorsConfiguration("/bar/**", config);

		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/foo/test.html").build());
		assertNull(this.configSource.getCorsConfiguration(exchange));

		exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/bar/test.html").build());
		assertEquals(config, this.configSource.getCorsConfiguration(exchange));
	}
