	@Test
	public void match() {
		TestHttpHandler handler1 = new TestHttpHandler();
		TestHttpHandler handler2 = new TestHttpHandler();
		TestHttpHandler handler3 = new TestHttpHandler();

		Map<String, HttpHandler> map = new HashMap<>();
		map.put("/path", handler1);
		map.put("/another/path", handler2);
		map.put("/yet/another/path", handler3);

		testHandle("/another/path/and/more", map);

		assertInvoked(handler2, "/another/path");
		assertNotInvoked(handler1, handler3);
	}
