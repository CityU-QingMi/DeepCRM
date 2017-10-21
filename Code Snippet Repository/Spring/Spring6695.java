	@Override
	public TopicConnection createTopicConnection(String username, String password) throws JMSException {
		ConnectionFactory target = obtainTargetConnectionFactory();
		if (target instanceof TopicConnectionFactory) {
			return ((TopicConnectionFactory) target).createTopicConnection(username, password);
		}
		else {
			Connection con = target.createConnection(username, password);
			if (!(con instanceof TopicConnection)) {
				throw new javax.jms.IllegalStateException("'targetConnectionFactory' is not a TopicConnectionFactory");
			}
			return (TopicConnection) con;
		}
	}
