	public void initConnection() throws JMSException {
		if (getTargetConnectionFactory() == null) {
			throw new IllegalStateException(
					"'targetConnectionFactory' is required for lazily initializing a Connection");
		}
		synchronized (this.connectionMonitor) {
			if (this.connection != null) {
				closeConnection(this.connection);
			}
			this.connection = doCreateConnection();
			prepareConnection(this.connection);
			if (this.startedCount > 0) {
				this.connection.start();
			}
			if (logger.isInfoEnabled()) {
				logger.info("Established shared JMS Connection: " + this.connection);
			}
		}
	}
