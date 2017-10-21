	@Test
	public void notFound() throws Exception {
		TestHttpHandler handler1 = new TestHttpHandler();
		TestHttpHandler handler2 = new TestHttpHandler();

		Map<String, HttpHandler> map = new HashMap<>();
		map.put("/path", handler1);
		map.put("/another/path", handler2);

		ServerHttpResponse response = testHandle("/yet/another/path", map);

		assertNotInvoked(handler1, handler2);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
