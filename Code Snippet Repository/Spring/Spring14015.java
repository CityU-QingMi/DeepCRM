	@Override
	public void sendMessageInternal(String message) throws SockJsTransportFailureException {
		// Open frame not sent yet?
		// If in the session initialization thread, then cache, otherwise wait.
		if (!this.openFrameSent) {
			synchronized (this.initSessionLock) {
				if (!this.openFrameSent) {
					this.initSessionCache.add(message);
					return;
				}
			}
		}

		cancelHeartbeat();
		writeFrame(SockJsFrame.messageFrame(getMessageCodec(), message));
		scheduleHeartbeat();
	}
