	@Override
	public void start() {
		if (!isRunning()) {
			if (this.webSocketClient instanceof Lifecycle) {
				((Lifecycle) this.webSocketClient).start();
			}
			else {
				this.running = true;
			}
		}
	}
