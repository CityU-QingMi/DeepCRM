	@Test
	public void replyWithCustomTimeToLive() throws JMSException {
		Session session = mock(Session.class);
		Queue replyDestination = mock(Queue.class);
		given(session.createQueue("queueOut")).willReturn(replyDestination);

		MessageProducer messageProducer = mock(MessageProducer.class);
		TextMessage responseMessage = mock(TextMessage.class);
		given(session.createTextMessage("Response")).willReturn(responseMessage);
		given(session.createProducer(replyDestination)).willReturn(messageProducer);

		MessagingMessageListenerAdapter listener = getPayloadInstance("Response", "replyPayloadToQueue", Message.class);
		QosSettings settings = new QosSettings();
		settings.setTimeToLive(6000);
		listener.setResponseQosSettings(settings);
		listener.onMessage(mock(javax.jms.Message.class), session);
		verify(session).createQueue("queueOut");
		verify(session).createTextMessage("Response");
		verify(messageProducer).send(responseMessage, javax.jms.Message.DEFAULT_DELIVERY_MODE,
				javax.jms.Message.DEFAULT_PRIORITY, 6000);
		verify(messageProducer).close();
	}
