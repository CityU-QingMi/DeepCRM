	@Override
	public ListenableFuture<WebSocketSession> connect(TransportRequest request, WebSocketHandler handler) {
		final SettableListenableFuture<WebSocketSession> future = new SettableListenableFuture<>();
		WebSocketClientSockJsSession session = new WebSocketClientSockJsSession(request, handler, future);
		handler = new ClientSockJsWebSocketHandler(session);
		request.addTimeoutTask(session.getTimeoutTask());

		URI url = request.getTransportUrl();
		WebSocketHttpHeaders headers = new WebSocketHttpHeaders(request.getHandshakeHeaders());
		if (logger.isDebugEnabled()) {
			logger.debug("Starting WebSocket session on " + url);
		}
		this.webSocketClient.doHandshake(handler, headers, url).addCallback(
				new ListenableFutureCallback<WebSocketSession>() {
					@Override
					public void onSuccess(@Nullable WebSocketSession webSocketSession) {
						// WebSocket session ready, SockJS Session not yet
					}
					@Override
					public void onFailure(Throwable ex) {
						future.setException(ex);
					}
				});
		return future;
	}
