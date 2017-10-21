	@Test
	public void checkNotModifiedETagWithSeparatorChars() {
		String eTag = "\"Foo, Bar\"";
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/").ifNoneMatch(eTag).build());

		assertTrue(exchange.checkNotModified(eTag));

		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(eTag, exchange.getResponse().getHeaders().getETag());
	}
