	private <T> WebSocketMessage toMessage(Type type, T message) {
		if (Type.TEXT.equals(type)) {
			byte[] bytes = ((String) message).getBytes(StandardCharsets.UTF_8);
			return new WebSocketMessage(Type.TEXT, session.bufferFactory().wrap(bytes));
		}
		else if (Type.BINARY.equals(type)) {
			DataBuffer buffer = session.bufferFactory().allocateBuffer().write((ByteBuffer[]) message);
			return new WebSocketMessage(Type.BINARY, buffer);
		}
		else if (Type.PONG.equals(type)) {
			DataBuffer buffer = session.bufferFactory().allocateBuffer().write((ByteBuffer[]) message);
			return new WebSocketMessage(Type.PONG, buffer);
		}
		else {
			throw new IllegalArgumentException("Unexpected message type: " + message);
		}
	}
