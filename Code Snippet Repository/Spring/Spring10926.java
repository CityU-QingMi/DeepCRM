	@Test
	public void checkNotModifiedUnpaddedETag() {
		String eTag = "Foo";
		String paddedEtag = String.format("\"%s\"", eTag);
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/").ifNoneMatch(paddedEtag).build());

		assertTrue(exchange.checkNotModified(eTag));

		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(paddedEtag, exchange.getResponse().getHeaders().getETag());
	}
