	@Test
	public void registerWebSocketHandler() throws Exception {
		WebSocketSession session = this.webSocketClient.doHandshake(
				new AbstractWebSocketHandler() {}, getWsBaseUrl() + "/ws").get();

		TestHandler serverHandler = this.wac.getBean(TestHandler.class);
		assertTrue(serverHandler.connectLatch.await(2, TimeUnit.SECONDS));

		session.close();
	}
