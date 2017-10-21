	@Test
	public void checkNotModifiedETagStrongWeak() {
		String eTag = "\"Foo\"";
		MockServerHttpRequest request = get("/").ifNoneMatch(String.format("W/%s", eTag)).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		assertTrue(exchange.checkNotModified(eTag));

		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(eTag, exchange.getResponse().getHeaders().getETag());
	}
