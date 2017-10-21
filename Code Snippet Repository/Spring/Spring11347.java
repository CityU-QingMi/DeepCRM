	@Override
	public void start() {
		if (this.externallyManaged) {
			return;
		}
		synchronized (this.lifecycleMonitor) {
			if (!isRunning()) {
				try {
					this.running = true;
					this.jettyClient.start();
				}
				catch (Exception ex) {
					throw new IllegalStateException("Failed to start Jetty WebSocketClient", ex);
				}
			}
		}
	}
