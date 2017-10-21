	@Override
	public void stop() {
		if (isRunning()) {
			this.running = false;
			for (TransportHandler handler : this.handlers.values()) {
				if (handler instanceof Lifecycle) {
					((Lifecycle) handler).stop();
				}
			}
		}
	}
