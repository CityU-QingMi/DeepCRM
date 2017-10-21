	@Test
	public void checkModifiedTimestamp() {
		Instant oneMinuteAgo = currentDate.minusSeconds(60);
		MockServerHttpRequest request = get("/").ifModifiedSince(oneMinuteAgo.toEpochMilli()).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		assertFalse(exchange.checkNotModified(currentDate));

		assertNull(exchange.getResponse().getStatusCode());
		assertEquals(currentDate.toEpochMilli(), exchange.getResponse().getHeaders().getLastModified());
	}
