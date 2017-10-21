	@Test
	public void shortcircuitFilter() throws Exception {

		TestFilter filter1 = new TestFilter();
		ShortcircuitingFilter filter2 = new ShortcircuitingFilter();
		TestFilter filter3 = new TestFilter();
		StubWebHandler targetHandler = new StubWebHandler();

		new FilteringWebHandler(targetHandler, Arrays.asList(filter1, filter2, filter3))
				.handle(MockServerWebExchange.from(MockServerHttpRequest.get("/").build()))
				.block(Duration.ZERO);

		assertTrue(filter1.invoked());
		assertTrue(filter2.invoked());
		assertFalse(filter3.invoked());
		assertFalse(targetHandler.invoked());
	}
