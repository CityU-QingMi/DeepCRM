	public void handleFrame(String payload) {
		SockJsFrame frame = new SockJsFrame(payload);
		switch (frame.getType()) {
			case OPEN:
				handleOpenFrame();
				break;
			case HEARTBEAT:
				if (logger.isTraceEnabled()) {
					logger.trace("Received heartbeat in " + this);
				}
				break;
			case MESSAGE:
				handleMessageFrame(frame);
				break;
			case CLOSE:
				handleCloseFrame(frame);
		}
	}
