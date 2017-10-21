	@Test
	public void sortsByQuality() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/path")
				.header("Accept", "text/plain; q=0.5, application/json")
				.build());

		List<MediaType> mediaTypes = Arrays.asList(TEXT_PLAIN, APPLICATION_JSON_UTF8);
		MediaType actual = this.resultHandler.selectMediaType(exchange, () -> mediaTypes);

		assertEquals(APPLICATION_JSON_UTF8, actual);
	}
