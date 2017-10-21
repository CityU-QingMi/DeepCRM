	@Override
	public void stop() {
		if (isRunning()) {
			this.running = false;
			if (this.factory != null) {
				try {
					this.factory.stop();
				}
				catch (Throwable ex) {
					throw new IllegalStateException("Unable to stop Jetty WebSocketServerFactory", ex);
				}
			}
		}
	}
