	private void testEcho(int messageCount, Transport transport, WebSocketHttpHeaders headers) throws Exception {
		List<TextMessage> messages = new ArrayList<>();
		for (int i = 0; i < messageCount; i++) {
			messages.add(new TextMessage("m" + i));
		}
		TestClientHandler handler = new TestClientHandler();
		initSockJsClient(transport);
		URI url = new URI(this.baseUrl + "/echo");
		WebSocketSession session = this.sockJsClient.doHandshake(handler, headers, url).get();
		for (TextMessage message : messages) {
			session.sendMessage(message);
		}
		handler.awaitMessageCount(messageCount, 5000);
		for (TextMessage message : messages) {
			assertTrue("Message not received: " + message, handler.receivedMessages.remove(message));
		}
		assertEquals("Remaining messages: " + handler.receivedMessages, 0, handler.receivedMessages.size());
		session.close();
	}
