	@Test
	public void getResourceFromSubDirectory() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("").build());
		setPathWithinHandlerMapping(exchange, "js/foo.js");
		this.handler.handle(exchange).block(TIMEOUT);

		assertEquals(MediaType.parseMediaType("application/javascript"),
				exchange.getResponse().getHeaders().getContentType());
		assertResponseBody(exchange, "function foo() { console.log(\"hello world\"); }");
	}
