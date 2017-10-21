	@Test
	public void unsolicitedPongWithEmptyPayload() throws Exception {
		String url = getWsBaseUrl() + "/ws";
		WebSocketSession session = this.webSocketClient.doHandshake(new AbstractWebSocketHandler() {}, url).get();

		TestWebSocketHandler serverHandler = this.wac.getBean(TestWebSocketHandler.class);
		serverHandler.setWaitMessageCount(1);

		session.sendMessage(new PongMessage());

		serverHandler.await();
		assertNull(serverHandler.getTransportError());
		assertEquals(1, serverHandler.getReceivedMessages().size());
		assertEquals(PongMessage.class, serverHandler.getReceivedMessages().get(0).getClass());
	}
