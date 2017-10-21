	protected AbstractClientSockJsSession(TransportRequest request, WebSocketHandler handler,
			SettableListenableFuture<WebSocketSession> connectFuture) {

		Assert.notNull(request, "'request' is required");
		Assert.notNull(handler, "'handler' is required");
		Assert.notNull(connectFuture, "'connectFuture' is required");
		this.request = request;
		this.webSocketHandler = handler;
		this.connectFuture = connectFuture;
	}
