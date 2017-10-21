	@Override
	protected boolean sendMessage(WebSocketMessage message) throws IOException {
		ByteBuffer buffer = message.getPayload().asByteBuffer();
		if (WebSocketMessage.Type.TEXT.equals(message.getType())) {
			getSendProcessor().setReadyToSend(false);
			String text = new String(buffer.array(), StandardCharsets.UTF_8);
			WebSockets.sendText(text, getDelegate(), new SendProcessorCallback());
		}
		else if (WebSocketMessage.Type.BINARY.equals(message.getType())) {
			getSendProcessor().setReadyToSend(false);
			WebSockets.sendBinary(buffer, getDelegate(), new SendProcessorCallback());
		}
		else if (WebSocketMessage.Type.PING.equals(message.getType())) {
			getSendProcessor().setReadyToSend(false);
			WebSockets.sendPing(buffer, getDelegate(), new SendProcessorCallback());
		}
		else if (WebSocketMessage.Type.PONG.equals(message.getType())) {
			getSendProcessor().setReadyToSend(false);
			WebSockets.sendPong(buffer, getDelegate(), new SendProcessorCallback());
		}
		else {
			throw new IllegalArgumentException("Unexpected message type: " + message.getType());
		}
		return true;
	}
