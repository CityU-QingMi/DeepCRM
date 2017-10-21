	@Test
	public void matchWithNativeContextPath() {
		MockServerHttpRequest request = MockServerHttpRequest
				.get("/yet/another/path")
				.contextPath("/yet")  // contextPath in underlying request
				.build();

		TestHttpHandler handler = new TestHttpHandler();
		Map<String, HttpHandler> map = Collections.singletonMap("/another/path", handler);

		new ContextPathCompositeHandler(map).handle(request, new MockServerHttpResponse());

		assertTrue(handler.wasInvoked());
		assertEquals("/yet/another/path", handler.getRequest().getPath().contextPath().value());
	}
