	@OnWebSocketFrame
	public void onWebSocketFrame(Frame frame) {
		if (OpCode.PONG == frame.getOpCode()) {
			ByteBuffer payload = frame.getPayload() != null ? frame.getPayload() : EMPTY_PAYLOAD;
			PongMessage message = new PongMessage(payload);
			try {
				this.webSocketHandler.handleMessage(this.wsSession, message);
			}
			catch (Throwable ex) {
				ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
			}
		}
	}
