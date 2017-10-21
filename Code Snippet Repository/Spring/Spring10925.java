	@Test
	public void checkModifiedETag() {
		String currentETag = "\"Foo\"";
		String oldEtag = "Bar";
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/").ifNoneMatch(oldEtag).build());

		assertFalse(exchange.checkNotModified(currentETag));

		assertNull(exchange.getResponse().getStatusCode());
		assertEquals(currentETag, exchange.getResponse().getHeaders().getETag());
	}
