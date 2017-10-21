	private <T> WebSocketMessage toMessage(T message) {
		WebSocketSession session = this.delegateSession;
		Assert.state(session != null, "Cannot create message without a session");
		if (message instanceof String) {
			byte[] bytes = ((String) message).getBytes(StandardCharsets.UTF_8);
			return new WebSocketMessage(Type.TEXT, session.bufferFactory().wrap(bytes));
		}
		else if (message instanceof ByteBuffer) {
			DataBuffer buffer = session.bufferFactory().wrap((ByteBuffer) message);
			return new WebSocketMessage(Type.BINARY, buffer);
		}
		else if (message instanceof PongMessage) {
			DataBuffer buffer = session.bufferFactory().wrap(((PongMessage) message).getApplicationData());
			return new WebSocketMessage(Type.PONG, buffer);
		}
		else {
			throw new IllegalArgumentException("Unexpected message type: " + message);
		}
	}
