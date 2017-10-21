	@Override
	public QueueConnection createQueueConnection() throws JMSException {
		Connection con;
		synchronized (this.connectionMonitor) {
			this.pubSubMode = Boolean.FALSE;
			con = createConnection();
		}
		if (!(con instanceof QueueConnection)) {
			throw new javax.jms.IllegalStateException(
					"This SingleConnectionFactory does not hold a QueueConnection but rather: " + con);
		}
		return ((QueueConnection) con);
	}
