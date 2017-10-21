	@Test
	public void emptySendTo() throws JMSException {
		MessagingMessageListenerAdapter listener = createDefaultInstance(String.class);

		TextMessage reply = mock(TextMessage.class);
		Session session = mock(Session.class);
		given(session.createTextMessage("content")).willReturn(reply);

		this.thrown.expect(ReplyFailureException.class);
		this.thrown.expectCause(Matchers.isA(InvalidDestinationException.class));
		listener.onMessage(createSimpleJmsTextMessage("content"), session);
	}
