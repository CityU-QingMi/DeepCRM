	@Test
	public void matchWithContextPathEqualToPath() {
		TestHttpHandler handler1 = new TestHttpHandler();
		TestHttpHandler handler2 = new TestHttpHandler();
		TestHttpHandler handler3 = new TestHttpHandler();

		Map<String, HttpHandler> map = new HashMap<>();
		map.put("/path", handler1);
		map.put("/another/path", handler2);
		map.put("/yet/another/path", handler3);

		testHandle("/path", map);

		assertInvoked(handler1, "/path");
		assertNotInvoked(handler2, handler3);
	}
