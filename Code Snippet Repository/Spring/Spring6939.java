	@Test
	public void processAndReply() throws JMSException {
		MessagingMessageListenerAdapter listener = createDefaultInstance(String.class);
		String body = "echo text";
		String correlationId = "link-1234";
		Destination replyDestination = new Destination() {};

		TextMessage reply = mock(TextMessage.class);
		QueueSender queueSender = mock(QueueSender.class);
		Session session = mock(Session.class);
		given(session.createTextMessage(body)).willReturn(reply);
		given(session.createProducer(replyDestination)).willReturn(queueSender);

		listener.setDefaultResponseDestination(replyDestination);
		StubTextMessage inputMessage = createSimpleJmsTextMessage(body);
		inputMessage.setJMSCorrelationID(correlationId);
		listener.onMessage(inputMessage, session);
		assertDefaultListenerMethodInvocation();

		verify(reply).setJMSCorrelationID(correlationId);
		verify(queueSender).send(reply);
		verify(queueSender).close();
	}
