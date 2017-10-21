	@Override
	public TopicConnection createTopicConnection() throws JMSException {
		ConnectionFactory target = obtainTargetConnectionFactory();
		if (target instanceof TopicConnectionFactory) {
			return ((TopicConnectionFactory) target).createTopicConnection();
		}
		else {
			Connection con = target.createConnection();
			if (!(con instanceof TopicConnection)) {
				throw new javax.jms.IllegalStateException("'targetConnectionFactory' is not a TopicConnectionFactory");
			}
			return (TopicConnection) con;
		}
	}
