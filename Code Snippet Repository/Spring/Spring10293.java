	@Override
	public final void afterPropertiesSet() throws Exception {
		Assert.notNull(this.host, "Host must not be null");
		Assert.isTrue(this.port >= 0, "Port must not be a negative number");
		Assert.isTrue(this.httpHandler != null || this.handlerMap != null, "No HttpHandler configured");
		Assert.state(!this.running, "Cannot reconfigure while running");

		synchronized (this.lifecycleMonitor) {
			initServer();
		}
	}
