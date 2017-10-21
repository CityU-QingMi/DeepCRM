	@Test
	public void nullSubProtocol() throws Exception {
		this.webSocketHandler.setDefaultProtocolHandler(defaultHandler);
		this.webSocketHandler.afterConnectionEstablished(session);

		verify(this.defaultHandler).afterSessionStarted(
				isA(ConcurrentWebSocketSessionDecorator.class), eq(this.inClientChannel));
		verify(this.stompHandler, times(0)).afterSessionStarted(session, this.inClientChannel);
		verify(this.mqttHandler, times(0)).afterSessionStarted(session, this.inClientChannel);
	}
