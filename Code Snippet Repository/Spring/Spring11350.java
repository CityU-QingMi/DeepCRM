	private Object createJettyHandler(URI url, WebSocketHandler handler, MonoProcessor<Void> completion) {
		return new JettyWebSocketHandlerAdapter(handler,
				session -> {
					UpgradeResponse response = session.getUpgradeResponse();
					HttpHeaders responseHeaders = new HttpHeaders();
					response.getHeaders().forEach(responseHeaders::put);
					HandshakeInfo info = afterHandshake(url, responseHeaders);
					return new JettyWebSocketSession(session, info, this.bufferFactory, completion);
				});
	}
