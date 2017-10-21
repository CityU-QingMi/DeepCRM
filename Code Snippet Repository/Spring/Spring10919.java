	@Test
	public void checkNotModifiedTimestampConditionalPutConflict() throws Exception {
		Instant oneMinuteAgo = currentDate.minusSeconds(60);
		long millis = oneMinuteAgo.toEpochMilli();
		MockServerHttpRequest request = MockServerHttpRequest.put("/").ifUnmodifiedSince(millis).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		assertTrue(exchange.checkNotModified(currentDate));
		assertEquals(412, exchange.getResponse().getStatusCode().value());
		assertEquals(-1, exchange.getResponse().getHeaders().getLastModified());
	}
