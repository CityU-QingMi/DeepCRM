	@Override
	protected void connectInternal(final TransportRequest transportRequest, final WebSocketHandler handler,
			final URI receiveUrl, final HttpHeaders handshakeHeaders, final XhrClientSockJsSession session,
			final SettableListenableFuture<WebSocketSession> connectFuture) {

		getTaskExecutor().execute(() -> {
			HttpHeaders httpHeaders = transportRequest.getHttpRequestHeaders();
			XhrRequestCallback requestCallback = new XhrRequestCallback(handshakeHeaders);
			XhrRequestCallback requestCallbackAfterHandshake = new XhrRequestCallback(httpHeaders);
			XhrReceiveExtractor responseExtractor = new XhrReceiveExtractor(session);
			while (true) {
				if (session.isDisconnected()) {
					session.afterTransportClosed(null);
					break;
				}
				try {
					if (logger.isTraceEnabled()) {
						logger.trace("Starting XHR receive request, url=" + receiveUrl);
					}
					getRestTemplate().execute(receiveUrl, HttpMethod.POST, requestCallback, responseExtractor);
					requestCallback = requestCallbackAfterHandshake;
				}
				catch (Throwable ex) {
					if (!connectFuture.isDone()) {
						connectFuture.setException(ex);
					}
					else {
						session.handleTransportError(ex);
						session.afterTransportClosed(new CloseStatus(1006, ex.getMessage()));
					}
					break;
				}
			}
		});
	}
