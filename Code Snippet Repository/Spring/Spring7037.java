	@Test
	public void replyPayloadNoDestination() throws JMSException {
		Queue replyDestination = mock(Queue.class);

		Session session = mock(Session.class);
		MessageProducer messageProducer = mock(MessageProducer.class);
		TextMessage responseMessage = mock(TextMessage.class);
		given(session.createTextMessage("Response")).willReturn(responseMessage);
		given(session.createProducer(replyDestination)).willReturn(messageProducer);

		MessagingMessageListenerAdapter listener =
				getPayloadInstance("Response", "replyPayloadNoDestination", Message.class);
		listener.setDefaultResponseDestination(replyDestination);
		listener.onMessage(mock(javax.jms.Message.class), session);

		verify(session, times(0)).createQueue(anyString());
		verify(session).createTextMessage("Response");
		verify(messageProducer).send(responseMessage);
		verify(messageProducer).close();
	}
