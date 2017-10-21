	@Override
	@Nullable
	protected Message<?> doReceive(Destination destination) {
		try {
			javax.jms.Message jmsMessage = obtainJmsTemplate().receive(destination);
			return convertJmsMessage(jmsMessage);
		}
		catch (JmsException ex) {
			throw convertJmsException(ex);
		}
	}
