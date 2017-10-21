	@Test
	public void checkNotModifiedTimestampConditionalPut() throws Exception {
		Instant oneMinuteAgo = currentDate.minusSeconds(60);
		long millis = currentDate.toEpochMilli();
		MockServerHttpRequest request = MockServerHttpRequest.put("/").ifUnmodifiedSince(millis).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		assertFalse(exchange.checkNotModified(oneMinuteAgo));
		assertNull(exchange.getResponse().getStatusCode());
		assertEquals(-1, exchange.getResponse().getHeaders().getLastModified());
	}
