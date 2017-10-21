	public TextMessage testReplyWithJackson(String methodName, String replyContent) throws JMSException {
		Queue replyDestination = mock(Queue.class);

		Session session = mock(Session.class);
		MessageProducer messageProducer = mock(MessageProducer.class);
		TextMessage responseMessage = mock(TextMessage.class);
		given(session.createTextMessage(replyContent)).willReturn(responseMessage);
		given(session.createProducer(replyDestination)).willReturn(messageProducer);

		MessagingMessageListenerAdapter listener = getPayloadInstance("Response", methodName, Message.class);
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTargetType(MessageType.TEXT);
		listener.setMessageConverter(messageConverter);
		listener.setDefaultResponseDestination(replyDestination);
		listener.onMessage(mock(javax.jms.Message.class), session);

		verify(session, times(0)).createQueue(anyString());
		verify(session).createTextMessage(replyContent);
		verify(messageProducer).send(responseMessage);
		verify(messageProducer).close();
		return responseMessage;
	}
