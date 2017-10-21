	@Test
	public void checkNotModifiedNon2xxStatus() {
		MockServerHttpRequest request = get("/").ifModifiedSince(this.currentDate.toEpochMilli()).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		exchange.getResponse().setStatusCode(HttpStatus.NOT_MODIFIED);

		assertFalse(exchange.checkNotModified(this.currentDate));
		assertEquals(304, exchange.getResponse().getStatusCode().value());
		assertEquals(-1, exchange.getResponse().getHeaders().getLastModified());
	}
