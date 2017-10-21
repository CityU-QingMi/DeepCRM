	@Test
	public void preflightRequestWithoutCorsConfigurationProvider() throws Exception {
		String origin = "http://domain2.com";
		ServerWebExchange exchange = createExchange(HttpMethod.OPTIONS, "/welcome.html", origin);
		Object actual = this.handlerMapping.getHandler(exchange).block();

		assertNotNull(actual);
		assertNotSame(this.welcomeController, actual);
		assertNull(exchange.getResponse().getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
	}
