	@Override
	public void start() {
		if (!isRunning()) {
			this.running = true;
			if (getWebSocketClient() instanceof Lifecycle) {
				((Lifecycle) getWebSocketClient()).start();
			}
		}

	}
