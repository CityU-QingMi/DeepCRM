	@Test
	public void asyncFilter() throws Exception {

		AsyncFilter filter = new AsyncFilter();
		StubWebHandler targetHandler = new StubWebHandler();

		new FilteringWebHandler(targetHandler, Collections.singletonList(filter))
				.handle(MockServerWebExchange.from(MockServerHttpRequest.get("/").build()))
				.block(Duration.ofSeconds(5));

		assertTrue(filter.invoked());
		assertTrue(targetHandler.invoked());
	}
