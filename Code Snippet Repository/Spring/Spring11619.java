	@Test
	public void getResourceFromSubDirectoryOfAlternatePath() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("").build());
		setPathWithinHandlerMapping(exchange, "js/baz.js");
		this.handler.handle(exchange).block(TIMEOUT);

		HttpHeaders headers = exchange.getResponse().getHeaders();
		assertEquals(MediaType.parseMediaType("application/javascript"), headers.getContentType());
		assertResponseBody(exchange, "function foo() { console.log(\"hello world\"); }");
	}
