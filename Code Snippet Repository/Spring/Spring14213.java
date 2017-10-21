	@Test
	public void connectWebSocket() throws Exception {
		setupInfoRequest(true);
		this.sockJsClient.doHandshake(handler, URL).addCallback(this.connectCallback);
		assertTrue(this.webSocketTransport.invoked());
		WebSocketSession session = mock(WebSocketSession.class);
		this.webSocketTransport.getConnectCallback().onSuccess(session);
		verify(this.connectCallback).onSuccess(session);
		verifyNoMoreInteractions(this.connectCallback);
	}
