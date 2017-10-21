	@SuppressWarnings("")
	private void invokeListener(JmsListenerEndpoint endpoint, Message message) throws JMSException {
		DefaultMessageListenerContainer messageListenerContainer = containerFactory.createListenerContainer(endpoint);
		Object listener = messageListenerContainer.getMessageListener();
		if (listener instanceof SessionAwareMessageListener) {
			((SessionAwareMessageListener<Message>) listener).onMessage(message, mock(Session.class));
		}
		else {
			((MessageListener) listener).onMessage(message);
		}
	}
