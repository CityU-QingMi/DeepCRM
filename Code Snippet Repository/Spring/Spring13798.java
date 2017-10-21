	@Override
	public void start() {
		synchronized (this.lifecycleMonitor) {
			if (!isRunning()) {
				try {
					if (logger.isInfoEnabled()) {
						logger.info("Starting Jetty WebSocketClient");
					}
					this.client.start();
				}
				catch (Exception ex) {
					throw new IllegalStateException("Failed to start Jetty client", ex);
				}
			}
		}
	}
