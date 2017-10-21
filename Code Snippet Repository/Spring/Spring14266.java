	@Test
	public void delegateMessagesWithErrorAndConnectionClosing() throws Exception {
		WebSocketHandler wsHandler = new ExceptionWebSocketHandlerDecorator(this.webSocketHandler);
		TestSockJsSession sockJsSession = new TestSockJsSession(
				"1", this.sockJsConfig, wsHandler, Collections.<String, Object>emptyMap());

		String msg1 = "message 1";
		String msg2 = "message 2";
		String msg3 = "message 3";

		willThrow(new IOException()).given(this.webSocketHandler).handleMessage(sockJsSession, new TextMessage(msg2));

		sockJsSession.delegateConnectionEstablished();
		try {
			sockJsSession.delegateMessages(msg1, msg2, msg3);
			fail("expected exception");
		}
		catch (SockJsMessageDeliveryException ex) {
			assertEquals(Collections.singletonList(msg3), ex.getUndeliveredMessages());
			verify(this.webSocketHandler).afterConnectionEstablished(sockJsSession);
			verify(this.webSocketHandler).handleMessage(sockJsSession, new TextMessage(msg1));
			verify(this.webSocketHandler).handleMessage(sockJsSession, new TextMessage(msg2));
			verify(this.webSocketHandler).afterConnectionClosed(sockJsSession, CloseStatus.SERVER_ERROR);
			verifyNoMoreInteractions(this.webSocketHandler);
		}
	}
