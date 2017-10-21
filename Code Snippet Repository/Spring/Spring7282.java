	@Override
	protected void stopInternal() {
		publishBrokerUnavailableEvent();
		if (this.tcpClient != null) {
			try {
				this.tcpClient.shutdown().get(5000, TimeUnit.MILLISECONDS);
			}
			catch (Throwable ex) {
				logger.error("Error in shutdown of TCP client", ex);
			}
		}
	}
