	public SockJsWebSocketHandler(SockJsServiceConfig serviceConfig, WebSocketHandler webSocketHandler,
			WebSocketServerSockJsSession sockJsSession) {

		Assert.notNull(serviceConfig, "serviceConfig must not be null");
		Assert.notNull(webSocketHandler, "webSocketHandler must not be null");
		Assert.notNull(sockJsSession, "session must not be null");

		this.sockJsServiceConfig = serviceConfig;
		this.sockJsSession = sockJsSession;

		webSocketHandler = WebSocketHandlerDecorator.unwrap(webSocketHandler);
		this.subProtocols = ((webSocketHandler instanceof SubProtocolCapable) ?
				new ArrayList<>(((SubProtocolCapable) webSocketHandler).getSubProtocols()) : Collections.emptyList());
	}
