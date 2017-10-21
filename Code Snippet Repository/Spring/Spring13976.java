	@Override
	public void start() {
		if (!isRunning()) {
			this.running = true;
			for (TransportHandler handler : this.handlers.values()) {
				if (handler instanceof Lifecycle) {
					((Lifecycle) handler).start();
				}
			}
		}
	}
