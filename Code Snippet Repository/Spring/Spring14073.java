	@Test
	public void registerWebSocketHandlerWithSockJS() throws Exception {
		WebSocketSession session = this.webSocketClient.doHandshake(
				new AbstractWebSocketHandler() {}, getWsBaseUrl() + "/sockjs/websocket").get();

		TestHandler serverHandler = this.wac.getBean(TestHandler.class);
		assertTrue(serverHandler.connectLatch.await(2, TimeUnit.SECONDS));

		session.close();
	}
