	@Override
	public void stop() {
		synchronized (this.lifecycleMonitor) {
			if (isRunning()) {
				this.running = false;
				if (this.factory != null) {
					try {
						this.factory.stop();
					}
					catch (Throwable ex) {
						throw new IllegalStateException("Failed to stop WebSocketServerFactory", ex);
					}
				}
			}
		}
	}
