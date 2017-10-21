	@Override
	public void stop() {
		synchronized (this.lifecycleMonitor) {
			if (logger.isInfoEnabled()) {
				logger.info("Stopping...");
			}
			stopInternal();
			this.clientInboundChannel.unsubscribe(this);
			this.brokerChannel.unsubscribe(this);
			if (this.clientInboundChannel instanceof InterceptableChannel) {
				((InterceptableChannel) this.clientInboundChannel).removeInterceptor(this.unsentDisconnectInterceptor);
			}
			this.running = false;
			logger.info("Stopped.");
		}
	}
