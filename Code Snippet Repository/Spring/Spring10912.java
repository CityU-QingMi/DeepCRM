	@Test
	public void checkModifiedETagAndNotModifiedTimestamp() throws Exception {
		String currentETag = "\"Foo\"";
		String oldEtag = "\"Bar\"";
		long time = currentDate.toEpochMilli();
		MockServerHttpRequest request = get("/").ifNoneMatch(oldEtag).ifModifiedSince(time).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		assertFalse(exchange.checkNotModified(currentETag, currentDate));

		assertNull(exchange.getResponse().getStatusCode());
		assertEquals(currentETag, exchange.getResponse().getHeaders().getETag());
		assertEquals(time, exchange.getResponse().getHeaders().getLastModified());
	}
