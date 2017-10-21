	@Override
	public QueueConnection createQueueConnection(String username, String password) throws JMSException {
		ConnectionFactory target = obtainTargetConnectionFactory();
		if (target instanceof QueueConnectionFactory) {
			return ((QueueConnectionFactory) target).createQueueConnection(username, password);
		}
		else {
			Connection con = target.createConnection(username, password);
			if (!(con instanceof QueueConnection)) {
				throw new javax.jms.IllegalStateException("'targetConnectionFactory' is not a QueueConnectionFactory");
			}
			return (QueueConnection) con;
		}
	}
