	@Override
	protected void disconnect(CloseStatus status) throws IOException {
		if (isActive()) {
			synchronized (this.disconnectLock) {
				if (isActive()) {
					this.disconnected = true;
					if (this.webSocketSession != null) {
						this.webSocketSession.close(status);
					}
				}
			}
		}
	}
