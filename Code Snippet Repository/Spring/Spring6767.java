	protected final void refreshSharedConnection() throws JMSException {
		synchronized (this.sharedConnectionMonitor) {
			ConnectionFactoryUtils.releaseConnection(
					this.sharedConnection, getConnectionFactory(), this.sharedConnectionStarted);
			this.sharedConnection = null;
			this.sharedConnection = createSharedConnection();
			if (this.sharedConnectionStarted) {
				this.sharedConnection.start();
			}
		}
	}
