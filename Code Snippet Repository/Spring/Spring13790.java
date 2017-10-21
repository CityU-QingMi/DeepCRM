	@Override
	public void onOpen(final javax.websocket.Session session, EndpointConfig config) {
		this.wsSession.initializeNativeSession(session);

		if (this.handler.supportsPartialMessages()) {
			session.addMessageHandler(new MessageHandler.Partial<String>() {
				@Override
				public void onMessage(String message, boolean isLast) {
					handleTextMessage(session, message, isLast);
				}
			});
			session.addMessageHandler(new MessageHandler.Partial<ByteBuffer>() {
				@Override
				public void onMessage(ByteBuffer message, boolean isLast) {
					handleBinaryMessage(session, message, isLast);
				}
			});
		}
		else {
			session.addMessageHandler(new MessageHandler.Whole<String>() {
				@Override
				public void onMessage(String message) {
					handleTextMessage(session, message, true);
				}
			});
			session.addMessageHandler(new MessageHandler.Whole<ByteBuffer>() {
				@Override
				public void onMessage(ByteBuffer message) {
					handleBinaryMessage(session, message, true);
				}
			});
		}

		session.addMessageHandler(new MessageHandler.Whole<javax.websocket.PongMessage>() {
			@Override
			public void onMessage(javax.websocket.PongMessage message) {
				handlePongMessage(session, message.getApplicationData());
			}
		});

		try {
			this.handler.afterConnectionEstablished(this.wsSession);
		}
		catch (Throwable ex) {
			ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
		}
	}
