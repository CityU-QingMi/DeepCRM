	@Nullable
	protected Message<?> doReceive(String destinationName) {
		try {
			javax.jms.Message jmsMessage = obtainJmsTemplate().receive(destinationName);
			return convertJmsMessage(jmsMessage);
		}
		catch (JmsException ex) {
			throw convertJmsException(ex);
		}
	}
