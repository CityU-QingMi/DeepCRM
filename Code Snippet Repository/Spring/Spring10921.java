	@Test
	public void checkNotModifiedTimestamp() throws Exception {
		MockServerHttpRequest request = get("/").ifModifiedSince(currentDate.toEpochMilli()).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		assertTrue(exchange.checkNotModified(currentDate));

		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(currentDate.toEpochMilli(), exchange.getResponse().getHeaders().getLastModified());
	}
