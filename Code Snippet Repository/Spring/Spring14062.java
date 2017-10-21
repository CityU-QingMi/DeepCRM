	@Test
	public void errorHandler() throws Exception {
		StompSubProtocolErrorHandler errorHandler = mock(StompSubProtocolErrorHandler.class);
		this.endpointRegistry.setErrorHandler(errorHandler);
		this.endpointRegistry.addEndpoint("/stompOverWebSocket");

		Map<String, SubProtocolHandler> protocolHandlers = this.webSocketHandler.getProtocolHandlerMap();
		StompSubProtocolHandler stompHandler = (StompSubProtocolHandler) protocolHandlers.get("v12.stomp");
		assertSame(errorHandler, stompHandler.getErrorHandler());
	}
