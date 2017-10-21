	public WebMvcStompEndpointRegistry(WebSocketHandler webSocketHandler,
			WebSocketTransportRegistration transportRegistration, TaskScheduler defaultSockJsTaskScheduler) {

		Assert.notNull(webSocketHandler, "WebSocketHandler is required ");
		Assert.notNull(transportRegistration, "WebSocketTransportRegistration is required");

		this.webSocketHandler = webSocketHandler;
		this.subProtocolWebSocketHandler = unwrapSubProtocolWebSocketHandler(webSocketHandler);

		if (transportRegistration.getSendTimeLimit() != null) {
			this.subProtocolWebSocketHandler.setSendTimeLimit(transportRegistration.getSendTimeLimit());
		}
		if (transportRegistration.getSendBufferSizeLimit() != null) {
			this.subProtocolWebSocketHandler.setSendBufferSizeLimit(transportRegistration.getSendBufferSizeLimit());
		}

		this.stompHandler = new StompSubProtocolHandler();
		if (transportRegistration.getMessageSizeLimit() != null) {
			this.stompHandler.setMessageSizeLimit(transportRegistration.getMessageSizeLimit());
		}

		this.sockJsScheduler = defaultSockJsTaskScheduler;
	}
