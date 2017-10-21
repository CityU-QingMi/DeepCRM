	@Override
	public QueueConnection createQueueConnection() throws JMSException {
		ConnectionFactory target = obtainTargetConnectionFactory();
		if (target instanceof QueueConnectionFactory) {
			return ((QueueConnectionFactory) target).createQueueConnection();
		}
		else {
			Connection con = target.createConnection();
			if (!(con instanceof QueueConnection)) {
				throw new javax.jms.IllegalStateException("'targetConnectionFactory' is not a QueueConnectionFactory");
			}
			return (QueueConnection) con;
		}
	}
