	@Test
	public void emptySubProtocol() throws Exception {
		this.session.setAcceptedProtocol("");
		this.webSocketHandler.setDefaultProtocolHandler(this.defaultHandler);
		this.webSocketHandler.afterConnectionEstablished(session);

		verify(this.defaultHandler).afterSessionStarted(
				isA(ConcurrentWebSocketSessionDecorator.class), eq(this.inClientChannel));
		verify(this.stompHandler, times(0)).afterSessionStarted(session, this.inClientChannel);
		verify(this.mqttHandler, times(0)).afterSessionStarted(session, this.inClientChannel);
	}
