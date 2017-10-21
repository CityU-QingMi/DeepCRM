	@Test
	public void replyPayloadToTopic() throws JMSException {
		Session session = mock(Session.class);
		Topic replyDestination = mock(Topic.class);
		given(session.createTopic("topicOut")).willReturn(replyDestination);

		MessageProducer messageProducer = mock(MessageProducer.class);
		TextMessage responseMessage = mock(TextMessage.class);
		given(session.createTextMessage("Response")).willReturn(responseMessage);
		given(session.createProducer(replyDestination)).willReturn(messageProducer);

		MessagingMessageListenerAdapter listener = getPayloadInstance("Response", "replyPayloadToTopic", Message.class);
		listener.onMessage(mock(javax.jms.Message.class), session);

		verify(session).createTopic("topicOut");
		verify(session).createTextMessage("Response");
		verify(messageProducer).send(responseMessage);
		verify(messageProducer).close();
	}
