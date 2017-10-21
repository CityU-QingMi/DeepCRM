	public void initConnection() throws ResourceException {
		if (getTargetConnectionFactory() == null) {
			throw new IllegalStateException(
					"'targetConnectionFactory' is required for lazily initializing a Connection");
		}
		synchronized (this.connectionMonitor) {
			if (this.target != null) {
				closeConnection(this.target);
			}
			this.target = doCreateConnection();
			prepareConnection(this.target);
			if (logger.isInfoEnabled()) {
				logger.info("Established shared CCI Connection: " + this.target);
			}
			this.connection = getCloseSuppressingConnectionProxy(this.target);
		}
	}
