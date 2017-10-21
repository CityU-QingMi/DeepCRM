	@Override
	public void stop() {
		synchronized (this.lifecycleMonitor) {
			if (isRunning()) {
				try {
					if (logger.isInfoEnabled()) {
						logger.info("Stopping Jetty WebSocketClient");
					}
					this.client.stop();
				}
				catch (Exception ex) {
					logger.error("Error stopping Jetty WebSocketClient", ex);
				}
			}
		}
	}
