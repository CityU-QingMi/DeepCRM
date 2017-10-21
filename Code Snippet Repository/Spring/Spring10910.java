	@Test
	public void checkNotModifiedETagAndTimestamp() {
		String eTag = "\"Foo\"";
		long time = currentDate.toEpochMilli();
		MockServerHttpRequest request = get("/").ifNoneMatch(eTag).ifModifiedSince(time).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		assertTrue(exchange.checkNotModified(eTag, currentDate));

		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(eTag, exchange.getResponse().getHeaders().getETag());
		assertEquals(time, exchange.getResponse().getHeaders().getLastModified());
	}
