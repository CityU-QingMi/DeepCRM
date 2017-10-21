	@Test
	public void checkNotModifiedETagWeakStrong() {
		String eTag = "\"Foo\"";
		String weakEtag = String.format("W/%s", eTag);
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/").ifNoneMatch(eTag).build());

		assertTrue(exchange.checkNotModified(weakEtag));

		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(weakEtag, exchange.getResponse().getHeaders().getETag());
	}
