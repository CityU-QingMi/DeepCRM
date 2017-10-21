	protected void stopSharedConnection() throws JMSException {
		synchronized (this.sharedConnectionMonitor) {
			this.sharedConnectionStarted = false;
			if (this.sharedConnection != null) {
				try {
					this.sharedConnection.stop();
				}
				catch (javax.jms.IllegalStateException ex) {
					logger.debug("Ignoring Connection stop exception - assuming already stopped: " + ex);
				}
			}
		}
	}
