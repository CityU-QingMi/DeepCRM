	private void testReceiveOneMessage(Transport transport, WebSocketHttpHeaders headers)
			throws Exception {

		TestClientHandler clientHandler = new TestClientHandler();
		initSockJsClient(transport);
		this.sockJsClient.doHandshake(clientHandler, headers, new URI(this.baseUrl + "/test")).get();
		TestServerHandler serverHandler = this.wac.getBean(TestServerHandler.class);

		assertNotNull("afterConnectionEstablished should have been called", clientHandler.session);
		serverHandler.awaitSession(5000);

		TextMessage message = new TextMessage("message1");
		serverHandler.session.sendMessage(message);
		clientHandler.awaitMessage(message, 5000);
	}
