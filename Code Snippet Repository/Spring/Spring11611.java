	@Test
	public void partialContentInvalidRangeHeader() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("").header("range", "bytes=foo bar").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		setPathWithinHandlerMapping(exchange, "foo.txt");

		StepVerifier.create(this.handler.handle(exchange))
				.expectNextCount(0)
				.expectComplete()
				.verify();

		assertEquals(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, exchange.getResponse().getStatusCode());
		assertEquals("bytes", exchange.getResponse().getHeaders().getFirst("Accept-Ranges"));
	}
