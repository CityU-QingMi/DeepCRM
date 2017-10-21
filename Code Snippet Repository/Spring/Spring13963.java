	@Override
	public void stop() {
		if (isRunning()) {
			if (this.webSocketClient instanceof Lifecycle) {
				((Lifecycle) this.webSocketClient).stop();
			}
			else {
				this.running = false;
			}
		}
	}
