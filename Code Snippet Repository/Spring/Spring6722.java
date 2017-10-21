	protected TopicConnection doCreateTopicConnection(
			@Nullable String username, @Nullable String password) throws JMSException {

		ConnectionFactory target = obtainTargetConnectionFactory();
		if (!(target instanceof TopicConnectionFactory)) {
			throw new javax.jms.IllegalStateException("'targetConnectionFactory' is not a TopicConnectionFactory");
		}
		TopicConnectionFactory queueFactory = (TopicConnectionFactory) target;
		if (StringUtils.hasLength(username)) {
			return queueFactory.createTopicConnection(username, password);
		}
		else {
			return queueFactory.createTopicConnection();
		}
	}
