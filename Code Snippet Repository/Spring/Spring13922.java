	@Override
	public final void sendMessage(WebSocketMessage<?> message) throws IOException {
		if (!(message instanceof TextMessage)) {
			throw new IllegalArgumentException(this + " supports text messages only.");
		}
		if (this.state != State.OPEN) {
			throw new IllegalStateException(this + " is not open: current state " + this.state);
		}

		String payload = ((TextMessage) message).getPayload();
		payload = getMessageCodec().encode(payload);
		payload = payload.substring(1);  // the client-side doesn't need message framing (letter "a")

		TextMessage messageToSend = new TextMessage(payload);
		if (logger.isTraceEnabled()) {
			logger.trace("Sending message " + messageToSend + " in " + this);
		}
		sendInternal(messageToSend);
	}
