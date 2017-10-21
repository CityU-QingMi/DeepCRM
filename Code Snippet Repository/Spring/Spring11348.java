	@Override
	public void stop() {
		if (this.externallyManaged) {
			return;
		}
		synchronized (this.lifecycleMonitor) {
			if (isRunning()) {
				try {
					this.running = false;
					this.jettyClient.stop();
				}
				catch (Exception ex) {
					throw new IllegalStateException("Error stopping Jetty WebSocketClient", ex);
				}
			}
		}
	}
