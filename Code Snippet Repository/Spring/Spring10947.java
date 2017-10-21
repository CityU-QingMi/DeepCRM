	@Test
	public void resolveSessionIdsWhenIdThenIdFound() {
		String id = "123";
		this.exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/path")
				.header(HeaderWebSessionIdResolver.DEFAULT_HEADER_NAME, id)
				.build());

		List<String> ids = this.idResolver.resolveSessionIds(this.exchange);

		assertEquals(Arrays.asList(id), ids);
	}
