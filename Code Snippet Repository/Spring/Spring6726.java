	@Nullable
	protected Message<?> doSendAndReceive(String destinationName, Message<?> requestMessage) {
		try {
			javax.jms.Message jmsMessage = obtainJmsTemplate().sendAndReceive(
					destinationName, createMessageCreator(requestMessage));
			return convertJmsMessage(jmsMessage);
		}
		catch (JmsException ex) {
			throw convertJmsException(ex);
		}
	}
