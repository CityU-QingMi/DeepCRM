	protected WebSocketFrame toFrame(WebSocketMessage message) {
		ByteBuf byteBuf = NettyDataBufferFactory.toByteBuf(message.getPayload());
		if (WebSocketMessage.Type.TEXT.equals(message.getType())) {
			return new TextWebSocketFrame(byteBuf);
		}
		else if (WebSocketMessage.Type.BINARY.equals(message.getType())) {
			return new BinaryWebSocketFrame(byteBuf);
		}
		else if (WebSocketMessage.Type.PING.equals(message.getType())) {
			return new PingWebSocketFrame(byteBuf);
		}
		else if (WebSocketMessage.Type.PONG.equals(message.getType())) {
			return new PongWebSocketFrame(byteBuf);
		}
		else {
			throw new IllegalArgumentException("Unexpected message type: " + message.getType());
		}
	}
