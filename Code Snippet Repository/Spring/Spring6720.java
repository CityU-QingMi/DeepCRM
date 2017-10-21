	protected QueueConnection doCreateQueueConnection(
			@Nullable String username, @Nullable String password) throws JMSException {

		ConnectionFactory target = obtainTargetConnectionFactory();
		if (!(target instanceof QueueConnectionFactory)) {
			throw new javax.jms.IllegalStateException("'targetConnectionFactory' is not a QueueConnectionFactory");
		}
		QueueConnectionFactory queueFactory = (QueueConnectionFactory) target;
		if (StringUtils.hasLength(username)) {
			return queueFactory.createQueueConnection(username, password);
		}
		else {
			return queueFactory.createQueueConnection();
		}
	}
