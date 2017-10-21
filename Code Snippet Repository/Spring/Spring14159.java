	private WebSocketHandler connect() {
		this.stompClient.connect("/foo", mock(StompSessionHandler.class));

		verify(this.stompSession).getSessionFuture();
		verifyNoMoreInteractions(this.stompSession);

		WebSocketHandler webSocketHandler = this.webSocketHandlerCaptor.getValue();
		assertNotNull(webSocketHandler);
		return webSocketHandler;
	}
