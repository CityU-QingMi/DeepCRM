	@Test
	public void zeroFilters() throws Exception {

		StubWebHandler targetHandler = new StubWebHandler();

		new FilteringWebHandler(targetHandler, Collections.emptyList())
				.handle(MockServerWebExchange.from(MockServerHttpRequest.get("/").build()))
				.block(Duration.ZERO);

		assertTrue(targetHandler.invoked());
	}
