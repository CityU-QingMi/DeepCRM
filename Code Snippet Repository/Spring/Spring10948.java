	@Test
	public void resolveSessionIdsWhenMultipleIdsThenIdsFound() {
		String id1 = "123";
		String id2 = "abc";
		this.exchange = MockServerWebExchange.from(
				MockServerHttpRequest.get("/path")
						.header(HeaderWebSessionIdResolver.DEFAULT_HEADER_NAME, id1, id2)
						.build());

		List<String> ids = this.idResolver.resolveSessionIds(this.exchange);

		assertEquals(Arrays.asList(id1, id2), ids);
	}
