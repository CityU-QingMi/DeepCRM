	@Test
	public void multipleFilters() throws Exception {

		TestFilter filter1 = new TestFilter();
		TestFilter filter2 = new TestFilter();
		TestFilter filter3 = new TestFilter();
		StubWebHandler targetHandler = new StubWebHandler();

		new FilteringWebHandler(targetHandler, Arrays.asList(filter1, filter2, filter3))
				.handle(MockServerWebExchange.from(MockServerHttpRequest.get("/").build()))
				.block(Duration.ZERO);

		assertTrue(filter1.invoked());
		assertTrue(filter2.invoked());
		assertTrue(filter3.invoked());
		assertTrue(targetHandler.invoked());
	}
