	@Test
	public void checkNotModifiedTimestampWithLengthPart() throws Exception {
		long epochTime = dateFormat.parse(CURRENT_TIME).getTime();
		String header = "Wed, 09 Apr 2014 09:57:42 GMT; length=13774";
		MockServerHttpRequest request = get("/").header("If-Modified-Since", header).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		assertTrue(exchange.checkNotModified(Instant.ofEpochMilli(epochTime)));

		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(epochTime, exchange.getResponse().getHeaders().getLastModified());
	}
