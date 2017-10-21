	@Override
	public void onOpen(Session session, EndpointConfig config) {
		this.delegateSession = this.sessionFactory.apply(session);
		Assert.state(this.delegateSession != null, "No delegate session");

		session.addMessageHandler(String.class, message -> {
			WebSocketMessage webSocketMessage = toMessage(message);
			this.delegateSession.handleMessage(webSocketMessage.getType(), webSocketMessage);
		});
		session.addMessageHandler(ByteBuffer.class, message -> {
			WebSocketMessage webSocketMessage = toMessage(message);
			this.delegateSession.handleMessage(webSocketMessage.getType(), webSocketMessage);
		});
		session.addMessageHandler(PongMessage.class, message -> {
			WebSocketMessage webSocketMessage = toMessage(message);
			this.delegateSession.handleMessage(webSocketMessage.getType(), webSocketMessage);
		});

		this.delegateHandler.handle(this.delegateSession).subscribe(this.delegateSession);
	}
