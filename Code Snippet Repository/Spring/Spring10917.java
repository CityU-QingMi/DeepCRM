	@Test
	public void checkModifiedTimestampWithLengthPart() throws Exception {
		long epochTime = dateFormat.parse(CURRENT_TIME).getTime();
		String header = "Tue, 08 Apr 2014 09:57:42 GMT; length=13774";
		MockServerHttpRequest request = get("/").header("If-Modified-Since", header).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		assertFalse(exchange.checkNotModified(Instant.ofEpochMilli(epochTime)));

		assertNull(exchange.getResponse().getStatusCode());
		assertEquals(epochTime, exchange.getResponse().getHeaders().getLastModified());
	}
