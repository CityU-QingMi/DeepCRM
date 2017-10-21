	@Test
	public void checkNotModifiedETagAndModifiedTimestamp() {
		String eTag = "\"Foo\"";
		Instant oneMinuteAgo = currentDate.minusSeconds(60);
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/")
				.ifNoneMatch(eTag)
				.ifModifiedSince(oneMinuteAgo.toEpochMilli())
				.build());

		assertTrue(exchange.checkNotModified(eTag, currentDate));

		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(eTag, exchange.getResponse().getHeaders().getETag());
		assertEquals(currentDate.toEpochMilli(), exchange.getResponse().getHeaders().getLastModified());
	}
