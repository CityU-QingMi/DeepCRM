	@Test
	public void preFlightWithCorsAwareHandler() throws Exception {
		String origin = "http://domain2.com";
		ServerWebExchange exchange = createExchange(HttpMethod.OPTIONS, "/cors.html", origin);
		Object actual = this.handlerMapping.getHandler(exchange).block();

		assertNotNull(actual);
		assertNotSame(this.corsController, actual);
		assertEquals("*", exchange.getResponse().getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
	}
