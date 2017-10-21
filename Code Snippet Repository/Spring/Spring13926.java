	private void handleMessageFrame(SockJsFrame frame) {
		if (!isOpen()) {
			if (logger.isErrorEnabled()) {
				logger.error("Ignoring received message due to state " + this.state + " in " + this);
			}
			return;
		}

		String[] messages = null;
		String frameData = frame.getFrameData();
		if (frameData != null) {
			try {
				messages = getMessageCodec().decode(frameData);
			}
			catch (IOException ex) {
				if (logger.isErrorEnabled()) {
					logger.error("Failed to decode data for SockJS \"message\" frame: " + frame + " in " + this, ex);
				}
				closeInternal(CloseStatus.BAD_DATA);
				return;
			}
		}
		if (messages == null) {
			return;
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Processing SockJS message frame " + frame.getContent() + " in " + this);
		}
		for (String message : messages) {
			if (isOpen()) {
				try {
					this.webSocketHandler.handleMessage(this, new TextMessage(message));
				}
				catch (Throwable ex) {
					logger.error("WebSocketHandler.handleMessage threw an exception on " + frame + " in " + this, ex);
				}
			}
		}
	}
