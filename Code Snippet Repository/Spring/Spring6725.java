	@Override
	@Nullable
	protected Message<?> doSendAndReceive(Destination destination, Message<?> requestMessage) {
		try {
			javax.jms.Message jmsMessage = obtainJmsTemplate().sendAndReceive(
					destination, createMessageCreator(requestMessage));
			return convertJmsMessage(jmsMessage);
		}
		catch (JmsException ex) {
			throw convertJmsException(ex);
		}
	}
