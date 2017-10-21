	@Test
	public void clientLifecycle() throws Exception {
		TestLifecycleWebSocketClient client = new TestLifecycleWebSocketClient(false);
		WebSocketHandler handler = new TextWebSocketHandler();
		WebSocketConnectionManager manager = new WebSocketConnectionManager(client, handler , "/a");

		manager.startInternal();
		assertTrue(client.isRunning());

		manager.stopInternal();
		assertFalse(client.isRunning());
	}
