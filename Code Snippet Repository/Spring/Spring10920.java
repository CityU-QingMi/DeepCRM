	@Test
	public void checkNotModifiedHeaderAlreadySet() {
		MockServerHttpRequest request = get("/").ifModifiedSince(currentDate.toEpochMilli()).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		exchange.getResponse().getHeaders().add("Last-Modified", CURRENT_TIME);

		assertTrue(exchange.checkNotModified(currentDate));
		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(1, exchange.getResponse().getHeaders().get("Last-Modified").size());
		assertEquals(CURRENT_TIME, exchange.getResponse().getHeaders().getFirst("Last-Modified"));
	}
