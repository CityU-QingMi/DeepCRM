	@Test
	public void replyPayloadToDestination() throws JMSException {
		Session session = mock(Session.class);
		MessageProducer messageProducer = mock(MessageProducer.class);
		TextMessage responseMessage = mock(TextMessage.class);
		given(session.createTextMessage("Response")).willReturn(responseMessage);
		given(session.createProducer(sharedReplyDestination)).willReturn(messageProducer);

		MessagingMessageListenerAdapter listener = getPayloadInstance("Response", "replyPayloadToDestination", Message.class);
		listener.onMessage(mock(javax.jms.Message.class), session);

		verify(session, times(0)).createQueue(anyString());
		verify(session).createTextMessage("Response");
		verify(messageProducer).send(responseMessage);
		verify(messageProducer).close();
	}
