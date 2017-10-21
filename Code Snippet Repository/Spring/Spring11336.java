	@OnWebSocketFrame
	public void onWebSocketFrame(Frame frame) {
		if (this.delegateSession != null) {
			if (OpCode.PONG == frame.getOpCode()) {
				ByteBuffer buffer = (frame.getPayload() != null ? frame.getPayload() : EMPTY_PAYLOAD);
				WebSocketMessage webSocketMessage = toMessage(Type.PONG, buffer);
				delegateSession.handleMessage(webSocketMessage.getType(), webSocketMessage);
			}
		}
	}
